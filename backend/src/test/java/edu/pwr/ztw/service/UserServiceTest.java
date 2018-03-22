package edu.pwr.ztw.service;

import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.pojos.UserRegistrationForm;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserServiceTest {

    User user;
    UserRegistrationForm form;
    ModelMapper modelMapper;


    @Test
    public void mapperShouldConvertFromFormToEntity(){
        user = new User();
        user.setLogin("andrzej");
        user.setEmail("andrzej@andrzej.andrzej");
        form = new UserRegistrationForm();
        form.setLogin("andrzej");
        form.setEmail("andrzej@andrzej.andrzej");
        form.setConfirmPassword("confirmPassword");
        modelMapper = new ModelMapper();
        User result = modelMapper.map(form,User.class);
        assertEquals(user.getLogin(),result.getLogin());
        assertEquals(user.getEmail(),result.getEmail());
        assertNull(result.getAvatar());
    }
}