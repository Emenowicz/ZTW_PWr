package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.service.TournamentService;
import edu.pwr.ztw.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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

    @RequestMapping(value = "tournament", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createTournament(OAuth2Authentication principal, @RequestBody @Valid Tournament tournament) {
        try {
            User currentUser = userService.getCurrentUser(principal);
            tournament.addOwner(currentUser);
            tournamentService.createTournament(tournament);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(tournament, HttpStatus.OK);
    }

    @RequestMapping(value = "tournament/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateTournament(@PathVariable("id") long id, @RequestBody @Valid Tournament tournament, OAuth2Authentication principal) {
        if (userService.getCurrentUser(principal) != tournament.getOwner())
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        try {
            tournament.setId(id);
            tournamentService.saveTournament(tournament);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(tournament);
    }

    @RequestMapping(value = "/tournament/{id}", method = RequestMethod.GET)
    public ResponseEntity getTournament(@PathVariable("id") long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if (tournament == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(tournament, HttpStatus.OK);
    }

    @RequestMapping(value = "/tournament/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeTournament(@PathVariable("id") long id, OAuth2Authentication principal) {
        if (userService.getCurrentUser(principal) != tournamentService.getTournamentById(id).getOwner())
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        try {
            tournamentService.removeTournament(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @RequestMapping(value = "/user/{id}/ownedtournaments", method = RequestMethod.GET)
    public List<Tournament> Tournaments(@PathVariable("id") String id) {
        return tournamentService.getAllTournamentsForUser(userService.getUserById(id));
    }

    @RequestMapping(value = "/tournament/{id}/match", method = RequestMethod.POST)
    public ResponseEntity addMatch(@PathVariable long id, @RequestBody @Valid Match match, OAuth2Authentication principal) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if (userService.getCurrentUser(principal) != tournament.getOwner())
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        try {
            tournament.getMatches().add(match);
            tournamentService.saveTournament(tournament);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(match, HttpStatus.OK);
    }

    @RequestMapping(value = "/tournament/{id}/matches", method = RequestMethod.GET)
    public Set<Match> getMatches(@PathVariable("id") long id) {
        return tournamentService.getTournamentById(id).getMatches();
    }
}
