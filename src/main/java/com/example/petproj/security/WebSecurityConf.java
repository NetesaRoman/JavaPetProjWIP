package com.example.petproj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    .httpBasic().and()
                .authorizeHttpRequests((requests) -> requests

                        .requestMatchers( "/login", "/welcome", "/registration","/user-photos/**","/styles/**","/fonts/**", "/img/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/userProfile", "/threads/**", "/create", "/showThread/**").hasRole("USER")
                        .requestMatchers( HttpMethod.POST,"/create").permitAll()


                )
                .formLogin((form) -> form
                        .loginPage("/login")

                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/userProfile")
                        .permitAll()
                )
                .exceptionHandling().accessDeniedPage("/403").and()
                .logout(LogoutConfigurer::permitAll)
                .logout().logoutSuccessUrl("/welcome")
        ;



        return http.build();
    }
}
