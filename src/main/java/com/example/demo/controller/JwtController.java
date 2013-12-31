package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// Responsible for creating the token for the first time
@RestController("/api")
public class JwtController {
	
	@PostMapping("/generateToken")
	public ResponseEntity<T> generateToken(){
		
	}
}
