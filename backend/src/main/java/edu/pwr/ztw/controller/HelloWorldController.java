package edu.pwr.ztw.controller;


import edu.pwr.ztw.entity.HelloWorld;
import edu.pwr.ztw.service.HelloWorldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldServiceImpl helloWorldService;

    @RequestMapping("/")
    public String index() {
        return "Strona główna!";
    }

    @RequestMapping(value= "/hello", method = RequestMethod.GET, produces = "application/json")
    public List<HelloWorld> helloWorld() {
        return helloWorldService.getAll();
    }
}
