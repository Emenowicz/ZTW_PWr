package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.Enums.PlayMode;
import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.service.TournamentSearchService;
import edu.pwr.ztw.service.TournamentService;
import edu.pwr.ztw.service.UserService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/tournaments")
public class TournamentController {
    @Resource
    private TournamentService tournamentService;
    @Resource
    private UserService userService;
    @Resource
    private TournamentSearchService tournamentSearchService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createTournament(OAuth2Authentication principal, @RequestBody @Valid Tournament tournament) {
        if (tournament == null) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        try {
            User currentUser = userService.getCurrentUser(principal);

            tournamentService.createTournament(tournament, currentUser);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(tournament, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getTournament(@PathVariable("id") long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if (tournament == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(tournament, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeTournament(@PathVariable("id") long id, OAuth2Authentication principal) {
        if (userService.getCurrentUser(principal) != tournamentService.getTournamentById(id).getOwner())
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        try {
            tournamentService.removeTournament(id);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }


    @RequestMapping(value = "/{id}/match", method = RequestMethod.POST)
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

    @RequestMapping(value = "/{id}/matches", method = RequestMethod.GET)
    public Set<Match> getMatches(@PathVariable("id") long id) {
        return tournamentService.getTournamentById(id).getMatches();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity findTournaments(@RequestParam(value = "q", defaultValue = "") String q, @RequestParam(value = "sd", defaultValue = "") String sd, @RequestParam(value = "ed", defaultValue = "") String ed, Pageable pageable) {
        Page<Tournament> resultPage;
        try {
            resultPage = tournamentSearchService.findPaginated(q, sd, ed, pageable);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        if (!resultPage.hasContent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(resultPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tId}/join", method = RequestMethod.POST)
    public ResponseEntity joinToTournament(@PathVariable("tId") long tId, OAuth2Authentication principal) {
        User currentUser = userService.getCurrentUser(principal);
        Tournament tournament = tournamentService.getTournamentById(tId);
        try {
            tournamentService.addUserToTournament(tournament, currentUser);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{tId}/start", method = RequestMethod.POST)
    public ResponseEntity startTournament(@PathVariable("tId") long tId, OAuth2Authentication principal) {
        User currentUser = userService.getCurrentUser(principal);
        Tournament tournament = tournamentService.getTournamentById(tId);
        boolean started = false;
        if (tournament.getOwner().equals(currentUser)) {
            switch (tournament.getPlayMode()) {
                case ONEVSONE:
                    if (tournament.getPlayers().size() >= 2) {
                        started = tournamentService.startTournament(tournament, PlayMode.ONEVSONE);
                    }
                    break;
                case TWOVSTWO:
                    if (tournament.getPlayers().size() >= 4 && tournament.getPlayers().size() % 2 != 1) {
                        started = tournamentService.startTournament(tournament, PlayMode.TWOVSTWO);
                    }
                    break;
            }
        }
        return started ? new ResponseEntity(tournament, HttpStatus.OK) : new ResponseEntity("Starting a tournament wasn't possible", HttpStatus.FORBIDDEN);

    }

    @RequestMapping(value = "/{owner}", method = RequestMethod.GET)
    public List<Tournament> getOwnedTournaments(@PathVariable("owner") String id) {
        return tournamentService.getAllTournamentsForUser(userService.getUserById(id));
    }

    @RequestMapping(value = "/{player}", method = RequestMethod.GET)
    public List<Tournament> getJoinedTournaments(@PathVariable("player") String id) {
        return tournamentService.getAllJoinedTournamentsForUser(userService.getUserById(id));
    }

}
