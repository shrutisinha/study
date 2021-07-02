package com.samplejwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("samplejwt".equals(username)) {
            String password = new BCryptPasswordEncoder().encode("password");
            return new User("samplejwt", password,
                    new ArrayList<>());
        } else if("samplejwt2".equals(username)) {
            String password = new BCryptPasswordEncoder().encode("password");
            return new User("samplejwt2", password,
                    new ArrayList<>());
        }{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}