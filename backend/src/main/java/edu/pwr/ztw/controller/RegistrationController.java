package edu.pwr.ztw.controller;

import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping("/register")
    public User register(@RequestBody @Valid User user){
        userService.registerUser(user);
        return user;
    }

    @GetMapping("/getUsers")
    public List getUsers(){
        return userService.getAllUsers();
    }
}
