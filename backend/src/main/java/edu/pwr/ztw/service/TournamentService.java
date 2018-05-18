package edu.pwr.ztw.service;

import edu.pwr.ztw.dao.MatchDao;
import edu.pwr.ztw.dao.TeamDao;
import edu.pwr.ztw.dao.TournamentDao;
import edu.pwr.ztw.entity.Enums.PlayMode;
import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.Team;
import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.entity.User;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TournamentService {
    @Resource
    TournamentDao tournamentDao;
    @Resource
    MatchService matchService;
    @Resource
    TeamService teamService;
    @Resource
    TeamDao teamDao;
    @Resource
    MatchDao matchDao;

    public void createTournament(Tournament tournament, User currentUser) {
        tournament.addOwner(currentUser);
        tournamentDao.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentDao.findAll();
    }

    public List<Tournament> getAllTournamentsForUser(User user) {
        return tournamentDao.findTournamentsByOwner(user);
    }

    public List<Tournament> getAllJoinedTournamentsForUser(User user) {
        return tournamentDao.findTournamentsByOwner(user);
    }

    public Tournament getTournamentById(long id) {
        return tournamentDao.findOne(id);
    }

    public List<Tournament> findTournamentsByName(String name) {
        return tournamentDao.findByNameContainingIgnoreCase(name);
    }

    public void saveTournament(Tournament tournament) {
        tournamentDao.save(tournament);
    }

    public void removeTournament(long id) throws NotFoundException {
        if(tournamentDao.findOne(id) != null){
            tournamentDao.delete(id);
        }else{
            throw new NotFoundException("Tournament not found");
        }
    }

    public void addUserToTournament(Tournament tournament, User currentUser) {
        tournament.addPlayer(currentUser);
        tournamentDao.save(tournament);
    }

    public boolean startTournament(Tournament tournament, PlayMode mode) {
        try{
            if(mode.equals(PlayMode.ONEVSONE)){
                generateMatches(tournament);
                return true;
            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return false;

    }

    private void generateMatches(Tournament tournament) {
        Set<User> players = tournament.getPlayers();
        List<Match> matches = matchService.generateMatchList(players.size(),tournament.getPlayMode());
        List<Team> teams = teamService.generateTeams(players, tournament.getPlayMode());
        Iterator<Team> teamIterator = teams.iterator();
        matches.forEach(m->{
            if(teamIterator.hasNext()){
                m.setTeamBlue(teamIterator.next());
            }
            if(teamIterator.hasNext()){
                m.setTeamRed(teamIterator.next());
            }
        });
        for(Match match : matches){
            if(teamIterator.hasNext()){
                match.setTeamRed(teamIterator.next());
            }
            if(teamIterator.hasNext()){
                match.setTeamBlue(teamIterator.next());
            }
        }
        tournament.setMatches(new HashSet<>(matches));
        tournament.setStarted(true);
        tournamentDao.save(tournament);
    }
}
