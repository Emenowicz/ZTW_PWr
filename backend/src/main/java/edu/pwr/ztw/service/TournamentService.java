package edu.pwr.ztw.service;

import edu.pwr.ztw.dao.TournamentDao;
import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class TournamentService {
    @Resource
    TournamentDao tournamentDao;

    public void createTournament(Tournament tournament){
        tournamentDao.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentDao.findAll();
    }

    public List<Tournament> getAllTournamentsForUser(User user){
        return tournamentDao.findTournamentsByOwner(user);
    }

    public Tournament getTournamentById(long id) {
        return tournamentDao.findOne(id);
    }

    public void updateTournament(Tournament tournament) {
        tournamentDao.save(tournament);
    }

    public void removeTournament(long id) {
        tournamentDao.delete(id);
    }
}
