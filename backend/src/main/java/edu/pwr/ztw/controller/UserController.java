package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
