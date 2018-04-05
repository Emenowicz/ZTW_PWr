package edu.pwr.ztw.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


}
