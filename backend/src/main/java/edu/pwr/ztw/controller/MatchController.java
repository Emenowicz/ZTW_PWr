package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.Match;
import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.service.MatchService;
import edu.pwr.ztw.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.Serializable;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Resource
    MatchService matchService;
    @Resource
    UserService userService;

    @RequestMapping(value = "{mId}/score", method = RequestMethod.POST)
    public ResponseEntity setScore(@PathVariable(name = "mId") long id, OAuth2Authentication principal, @RequestBody MatchScore score) {
        User currentUser = userService.getCurrentUser(principal);
        Match match = matchService.getMatchById(id);
        if (matchService.getPlayers(match).contains(currentUser)) {
            matchService.setScoreByPlayer(match, score.getBlue(), score.getRed(), currentUser);
        } else if (match.getTournament().getOwner().equals(currentUser)) {
            matchService.setScoreByTournamentOwner(match, score.getBlue(), score.getRed(), currentUser);
        } else {
            return new ResponseEntity("You can not add score to this match", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(match, HttpStatus.OK);
    }

    @RequestMapping(value = "{mId}", method = RequestMethod.GET)
    public ResponseEntity getMatch(@PathVariable(name = "mId") long id, OAuth2Authentication principal) {
        Match match = matchService.getMatchById(id);
        if (match != null) {
            return new ResponseEntity(match, HttpStatus.OK);
        } else {
            return new ResponseEntity("Match not found", HttpStatus.NOT_FOUND);
        }
    }

    private static class MatchScore implements Serializable {
        int red;
        int blue;

        public MatchScore() {
        }

        public int getRed() {
            return red;
        }

        public void setRed(int scoreRed) {
            this.red = scoreRed;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(int blue) {
            this.blue = blue;
        }
    }
}
