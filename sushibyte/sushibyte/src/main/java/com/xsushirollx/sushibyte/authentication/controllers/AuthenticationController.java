package com.xsushirollx.sushibyte.authentication.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.xsushirollx.sushibyte.authentication.dto.AuthenticationRequest;
import com.xsushirollx.sushibyte.authentication.dto.AuthenticationResponse;
import com.xsushirollx.sushibyte.authentication.services.UserDetailServiceImpl;
import com.xsushirollx.sushibyte.authentication.utils.JwtUtil;

@RestController
public class AuthenticationController {
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserDetailServiceImpl userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
	static Logger log = LogManager.getLogger(AuthenticationController.class.getName());
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( 
					authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));			
		}
		catch(Exception e) {
			log.warn(e.getMessage());
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
