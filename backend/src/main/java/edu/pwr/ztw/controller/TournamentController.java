package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.service.TournamentService;
import edu.pwr.ztw.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class TournamentController {
    @Resource
    private TournamentService tournamentService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/{userId}/tournament",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Tournament createTournament(@PathVariable("userId") String userId, @Valid Tournament tournament){
        tournament.addOwner(userService.getUserById(userId));
        tournamentService.createTournament(tournament);
        return tournament;
    }

    @RequestMapping(value = "/tournament/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Tournament getTournament(@PathVariable("id") long id){
        return tournamentService.getTournamentById(id);
    }

    @RequestMapping(value = "/tournament/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeTournament(@PathVariable("id") long id){
        tournamentService.removeTournament(id);
    }

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public List<Tournament> getAllTournaments(){
        return tournamentService.getAllTournaments();
    }

    @RequestMapping(value = "/user/{id}/ownedtournaments", method = RequestMethod.GET)
    public List<Tournament> Tournaments(@PathVariable("id") String id){
        return tournamentService.getAllTournamentsForUser(userService.getUserById(id));
    }

    @RequestMapping(value = "/tournament/{id}/match", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Match addMatch(@PathVariable long id, Match match){
        Tournament tournament = tournamentService.getTournamentById(id);
        tournament.getMatches().add(match);
        tournamentService.saveTournament(tournament);
        return match;
    }

    @RequestMapping(value = "/tournament/{id}/matches", method = RequestMethod.GET)
    public Set<Match> getMatches(@PathVariable("id") long id){
        return tournamentService.getTournamentById(id).getMatches();
    }
}
