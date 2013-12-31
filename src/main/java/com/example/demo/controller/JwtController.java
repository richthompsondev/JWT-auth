package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.service.CustomUserDetailService;
import com.example.demo.util.JwtUtil;

// Responsible for creating the token for the first time
@RestController
@RequestMapping("/api")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;
	
	@PostMapping("/generateToken")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtResquest){
		// Authenticate the user
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtResquest.getUserName(), jwtResquest.getPassword()));
		
		UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtResquest.getUserName());
		
		String jwtToken = jwtUtil.generateToken(userDetails);
		
		JwtResponse jwtResponse = new JwtResponse(jwtToken);
		
		//return ResponseEntity.ok(jwtResponse);
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}
}
