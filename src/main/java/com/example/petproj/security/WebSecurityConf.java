package com.example.petproj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*
 *
 * @author Roman Netesa
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConf {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
