package com.xsushirollx.sushibyte.authentication.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xsushirollx.sushibyte.authentication.dto.AuthRequest;

@RestController
public class AuthenticationController {
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthRequest request){
		
		return null;
	}
}
