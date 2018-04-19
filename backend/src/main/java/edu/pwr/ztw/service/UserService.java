package edu.pwr.ztw.service;

import edu.pwr.ztw.dao.UserDao;
import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.pojos.UserRegistrationForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getAllUsers(){
       return userDao.findAll();
    }

    public User getCurrentUser(OAuth2Authentication principal){
        return userDao.findOne(((Map)principal.getUserAuthentication().getDetails()).get("sub").toString());
    }

    public void createUser(OAuth2Authentication principal){
        String id = ((Map)principal.getUserAuthentication().getDetails()).get("sub").toString();
        if(!userDao.exists(id)) {
            User user = new User(id,principal.getName());
            userDao.save(user);
        }
    }

    public User getUserById(String id) {
        return userDao.findOne(id);
    }
}
