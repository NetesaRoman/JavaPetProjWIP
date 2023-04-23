package com.example.petproj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





    @Bean
    public DaoAuthenticationProvider authProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean

    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    .httpBasic().and()
                .authorizeHttpRequests(requests -> requests

                        .requestMatchers( "/login", "/welcome", "/registration","/user-photos/**","/styles/**","/fonts/**", "/img/**").permitAll()
                        .requestMatchers("/userProfile/**","/users/**","/deleteUser/**",  "/threads/**", "/deleteThread/**", "/create", "/showThread/**", "/like/**", "/dislike/**").hasAnyRole("USER", "ADMIN", "MODERATOR")




                )
                .formLogin(form -> form
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
