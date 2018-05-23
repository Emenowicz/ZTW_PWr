package edu.pwr.ztw.service;

import edu.pwr.ztw.dao.UserDao;
import edu.pwr.ztw.entity.Enums.PlayMode;
import edu.pwr.ztw.entity.Team;
import edu.pwr.ztw.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TeamService {
    @Resource
    UserDao userDao;
    public List<Team> generateTeams(Set<User> players, PlayMode playMode) {
        List<Team> teams = new ArrayList<>();
        Iterator<User> userIterator = players.iterator();
        while (userIterator.hasNext()) {
            Team team = new Team();
            team.setPlayerOne(userIterator.next());
            if (playMode.equals(PlayMode.TWOVSTWO) && userIterator.hasNext()) {
                team.setPlayerTwo(userIterator.next());
            }
            teams.add(team);
        }
        return teams;
    }

    public Set<User> getPlayers(Team team){
        Set<User> players = new HashSet<>();
        players.add(team.getPlayerOne());
        players.add(team.getPlayerTwo());
        return players;
    }
}
