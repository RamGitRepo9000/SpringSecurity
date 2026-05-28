package com.Security.Securitytest.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public UserDetailsService userdetservice;

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authprdr = new DaoAuthenticationProvider(userdetservice);
        //authprdr.setUserDetailsPasswordService(Objects.requireNonNull(userdetservice.loadUserByUsername().getPassword()));
        authprdr.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return authprdr;
    }

    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity http)throws Exception{
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(req -> req.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    //@Bean
   /* public UserDetailsService getUesrs(){
         UserDetails user= User.withDefaultPasswordEncoder().username("Ramu1")
                .password("Ramu1").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }*/
}
