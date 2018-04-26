package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.service.TournamentService;
import edu.pwr.ztw.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    TournamentService tournamentService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}/ownedtournaments", method = RequestMethod.GET)
    public List<Tournament> getOwnedTournaments(@PathVariable("id") String id) {
        return tournamentService.getAllTournamentsForUser(userService.getUserById(id));
    }

    @RequestMapping(value = "/{id}/joinedtournaments", method = RequestMethod.GET)
    public List<Tournament> getJoinedTournaments(@PathVariable("id") String id) {
        return tournamentService.getAllJoinedTournamentsForUser(userService.getUserById(id));
    }
}
