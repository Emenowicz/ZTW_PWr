package edu.pwr.ztw.controller;

import edu.pwr.ztw.pojos.UserRegistrationForm;
import edu.pwr.ztw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationForm form){
        if(!form.getPassword().equals(form.getConfirmPassword())){
            return new ResponseEntity<>("Password doesn't match",HttpStatus.CONFLICT);
        }else if(userService.getUserByLogin(form.getLogin())!=null){
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        userService.registerUser(form);
        return new ResponseEntity<>("Succesfuly registered", HttpStatus.OK);
    }

//    For presentation purposes
    @GetMapping("/getUsers")
    public List getUsers(){
        return userService.getAllUsers();
    }
}
