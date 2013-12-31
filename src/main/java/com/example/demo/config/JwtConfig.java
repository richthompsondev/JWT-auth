package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.CustomUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class JwtConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	// Says how we want to manage our authentication process
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
	}

	// Controls which end-points are permitted and which are not permitted
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Configuration
		http
				.csrf()
				.disable()
				.cors()
				.disable()
				.authorizeRequests()
				.antMatchers("/api/generateToken").permitAll()
				// For any requests, authentication should be performed
				.anyRequest().authenticated() 
				.and()
				// Every request should be independent of other and server doesn't have to manage session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
	}
	@Bean // So the object will be created
	// To make sure our passwords is secured
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // Not use in production, just the make simple 4now
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
