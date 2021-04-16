package com.xsushirollx.sushibyte.authentication.controllers;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.xsushirollx.sushibyte.authentication.config.JwtTokenUtil;
import com.xsushirollx.sushibyte.authentication.dto.AuthRequest;

@RestController
public class AuthenticationController {
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	AuthenticationManager authenticationManager;
	static Logger log = LogManager.getLogger(AuthenticationController.class.getName());
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthRequest request){
		String jwtToken = null;
		try {
			//not complete
			Authentication authenticate = authenticationManager.authenticate(null);
		}
		catch (BadCredentialsException e) {
			log.log(Level.WARN, e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return new ResponseEntity<String>(jwtToken,HttpStatus.OK);
	}
}
