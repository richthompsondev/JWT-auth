package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JwtRequest;
import com.example.demo.service.CustomUserDetailService;
import com.example.demo.util.JwtUtil;

// Responsible for creating the token for the first time
@RestController("/api")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtResquest){
		// Authenticate the user
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtResquest.getUserName(), jwtResquest.getPassword()));
		
		UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtResquest.getUserName());
		
		String jwtToken = jwtUtil.generateToken(userDetails);
		
		return null;
	}
}
