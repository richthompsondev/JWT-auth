package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    // Actually does the validation for user existence
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // TODO user object
        // Can make a DB call with help of repository and do the validation
        if (userName.equals("John")) {
            // Assume these are returned from DB upon success
            return new User("John", "secret", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User does not exist!");
        }
    }

}
