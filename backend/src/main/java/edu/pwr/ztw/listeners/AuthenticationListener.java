package edu.pwr.ztw.listeners;

import edu.pwr.ztw.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthenticationListener {

    @Resource
    private UserService userService;

    @EventListener(AuthenticationSuccessEvent.class)
    public void onApplicationEvent(ApplicationEvent appEvent)
    {
//        if (appEvent instanceof AuthenticationSuccessEvent)
//        {
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
            userService.createUserIfNotExists((OAuth2Authentication) event.getAuthentication());

//        }
    }
}
