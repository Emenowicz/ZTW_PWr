package edu.pwr.ztw.service;

import edu.pwr.ztw.dao.UserDao;
import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.pojos.UserRegistrationForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    private ModelMapper modelMapper = new ModelMapper();

    public void registerUser(UserRegistrationForm form){
        userDao.save(modelMapper.map(form,User.class));
    }

    public List getAllUsers(){
       return userDao.findAll();
    }

    public User getUserByLogin(String login){
        return userDao.getUserByLogin(login);
    }
}
