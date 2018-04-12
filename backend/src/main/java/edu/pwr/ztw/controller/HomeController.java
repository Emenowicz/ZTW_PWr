package edu.pwr.ztw.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
public class HomeController {

    @RequestMapping("/me")
    public Object user(Principal principal){
        return principal;
    }

}
