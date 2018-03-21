package edu.pwr.ztw.service;

import edu.pwr.ztw.dao.HelloWorldDAO;
import edu.pwr.ztw.entity.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloWorldServiceImpl {

    @Autowired
    private HelloWorldDAO helloWorldDAO;

    public List<HelloWorld> getAll(){
        List<HelloWorld> result = helloWorldDAO.getAll();
        return result;
    }
}
