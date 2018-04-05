//package edu.pwr.ztw.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter{
//    private static final String[] AUTH_WHITELIST = {
//            "/auth/*",
//            // -- swagger ui
//            "/swagger-resources/**",
//            "/swagger-ui.html",
//            "/v2/api-docs",
//            "/webjars/**"
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/register","/hello", "/getUsers").permitAll()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//}
