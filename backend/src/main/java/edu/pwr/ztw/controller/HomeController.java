package edu.pwr.ztw.controller;


import edu.pwr.ztw.service.UserService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class HomeController {

    @Resource
    UserService userService;

    @RequestMapping("/me")
    public Object user(OAuth2Authentication principal){
        userService.createUser(principal);
        return principal;
    }


}
