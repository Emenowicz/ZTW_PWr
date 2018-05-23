package edu.pwr.ztw.service;

import edu.pwr.ztw.dao.MatchDao;
import edu.pwr.ztw.entity.Enums.PlayMode;
import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchService {
    @Resource
    MatchDao matchDao;
    @Resource
    TeamService teamService;

    public List<Match> generateMatchList(int size, PlayMode playMode) {
        List<Match> matches = new ArrayList<>();
        int matchesCount;
        if (playMode.equals(PlayMode.ONEVSONE)) {
            matchesCount = size / 2;
        } else {
            matchesCount = size / 4;
        }
        for (int i = 0; i < matchesCount; i++) {
            matches.add(new Match());
        }
        return matches;
    }

    public Set<User> getPlayers(Match match) {
        Set<User> players = new HashSet<>();
        players.add(match.getTeamBlue().getPlayerOne());
        if (match.getTeamBlue().getPlayerTwo() != null) {
            players.add(match.getTeamBlue().getPlayerTwo());
        }
        players.add(match.getTeamRed().getPlayerOne());
        if (match.getTeamBlue().getPlayerTwo() != null) {
            players.add(match.getTeamRed().getPlayerTwo());
        }
        return players;
    }

    public void setScoreByTournamentOwner(Match match, int scoreBlue, int scoreRed, User user) {
        match.setScoreBlue(scoreBlue);
        match.setScoreRed(scoreRed);
        match.setAcceptedBlue(true);
        match.setAcceptedRed(true);
        match.setScoreUpdatedBy(user);
        matchDao.save(match);
    }

    public void setScoreByPlayer(Match match, int scoreBlue, int scoreRed, User currentUser) {
        if (!match.isAcceptedRed() && teamService.getPlayers(match.getTeamRed()).contains(currentUser)) {
            match.setScoreBlue(scoreBlue);
            match.setScoreRed(scoreRed);
            match.setAcceptedBlue(false);
            match.setAcceptedRed(true);
            match.setScoreUpdatedBy(currentUser);

        } else if (!match.isAcceptedBlue() && teamService.getPlayers(match.getTeamBlue()).contains(currentUser)) {
            match.setScoreBlue(scoreBlue);
            match.setScoreRed(scoreRed);
            match.setAcceptedBlue(true);
            match.setAcceptedRed(false);
            match.setScoreUpdatedBy(currentUser);
        }
        matchDao.save(match);
    }

    public void acceptScore(Match match, User currentUser) {
        if (match.isAcceptedBlue() && !match.isAcceptedRed() && teamService.getPlayers(match.getTeamRed()).contains(currentUser)) {
            match.setAcceptedRed(true);
        } else if (match.isAcceptedRed() && !match.isAcceptedBlue() && teamService.getPlayers(match.getTeamBlue()).contains(currentUser)) {
            match.setAcceptedBlue(true);
        }
        matchDao.save(match);
    }

    public Match getMatchById(long id) {
        return matchDao.findOne(id);
    }
}
