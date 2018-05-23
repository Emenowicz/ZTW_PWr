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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Resource
    MatchService matchService;
    @Resource
    UserService userService;

    @RequestMapping(value = "{mId}/score", method = RequestMethod.POST)
    public ResponseEntity setScore(@PathVariable(name = "mId") long id, OAuth2Authentication principal, int scoreBlue, int scoreRed) {
        User currentUser = userService.getCurrentUser(principal);
        Match match = matchService.getMatchById(id);
        if (matchService.getPlayers(match).contains(currentUser)) {
            matchService.setScoreByPlayer(match,scoreBlue,scoreRed,currentUser);
        } else if(match.getTournament().getOwner().equals(currentUser)) {
            matchService.setScoreByTournamentOwner(match,scoreBlue,scoreRed, currentUser);
        }
        else{
            return new ResponseEntity("You can not add score to this match",HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(match,HttpStatus.OK);
    }

    @RequestMapping(value = "{mId}/acceptScore",method = RequestMethod.POST)
    public ResponseEntity acceptScore(@PathVariable(name = "mId") long id, OAuth2Authentication principal){
        User currentUser = userService.getCurrentUser(principal);
        Match match = matchService.getMatchById(id);
        if(matchService.getPlayers(match).contains(currentUser)){
            matchService.acceptScore(match,currentUser);
        }else{
            return new ResponseEntity("You are not part of team and can not accept score", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(match,HttpStatus.OK);
    }
}
