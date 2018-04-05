package edu.pwr.ztw;

import edu.pwr.ztw.dao.UserDao;
import edu.pwr.ztw.entity.Role;
import edu.pwr.ztw.entity.User;
import edu.pwr.ztw.pojos.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import java.util.Arrays;


@SpringBootApplication
public class ZtwApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtwApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserDao userDao) throws Exception {
		if(userDao.count() == 0){
			userDao.save(new User("user","password","user@email.com", Arrays.asList(new Role("USER"),new Role("ACTUATOR"))));
		}
		builder.userDetailsService(s -> new CustomUserDetails(userDao.getUserByLogin(s)));

	}
}
