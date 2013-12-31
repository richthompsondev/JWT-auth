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
		if(userName.equals("John")) {
			// Can make a DB call with help of repository and do the validation
			return new User("John", "password", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User does not exist!");
		}
	}
	
}
