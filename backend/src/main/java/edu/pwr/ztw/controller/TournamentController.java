package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.service.TournamentService;
import edu.pwr.ztw.service.UserService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
public class TournamentController {
    @Resource
    private TournamentService tournamentService;
    @Resource
    private UserService userService;

    @PostMapping("/create_tournament")
    public Tournament createTournament(OAuth2Authentication principal, Tournament tournament){
        tournament.setOwner(userService.getCurrentUser(principal));
        tournamentService.createTournament(tournament);
        return tournament;
    }

    @GetMapping("/all_tournaments")
    public List<Tournament> Tournaments(){
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/all_user_tournaments")
    public List<Tournament> Tournaments(OAuth2Authentication principal){
        return tournamentService.getAllTournamentsForCurrentUser(userService.getCurrentUser(principal));
    }

    @PostMapping("/add_match")
    public Set<Match> addMatch(long tournamentId, Match match){
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        tournament.getMatches().add(match);
        tournamentService.updateTournament(tournament);
        return tournament.getMatches();
    }


}
