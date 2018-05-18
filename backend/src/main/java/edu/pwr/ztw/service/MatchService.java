package edu.pwr.ztw.service;

import edu.pwr.ztw.entity.Enums.PlayMode;
import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.Round;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchService {
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
        for (Match match : matches){
            Set<Round> rounds = new HashSet<>();
            rounds.add(new Round());
            rounds.add(new Round());
            rounds.add(new Round());
            match.setRounds(rounds);
        }
        return matches;
    }
}
