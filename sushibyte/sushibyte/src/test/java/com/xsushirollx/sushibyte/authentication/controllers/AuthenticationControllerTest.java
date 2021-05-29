package com.xsushirollx.sushibyte.authentication.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsushirollx.sushibyte.authentication.dto.AuthenticationRequest;
import com.xsushirollx.sushibyte.authentication.entities.User;
import com.xsushirollx.sushibyte.authentication.entities.UserDetailsImpl;
import com.xsushirollx.sushibyte.authentication.services.UserDetailServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
	@MockBean
	UserDetailServiceImpl userDetailsService;
	@MockBean
	AuthenticationManager authenticationManager;
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void authenticateTestOnSuccess() throws JsonProcessingException, Exception {
		AuthenticationRequest request = new AuthenticationRequest();
		request.setUsername("username");
		request.setPassword("password");
		UserDetails userDetails = new UserDetailsImpl(new User());
		when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);
		mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
		.andExpect(status().isOk());
	}
	
	@Test
	void authenticateTestOnFail() throws JsonProcessingException, Exception {
		AuthenticationRequest request = new AuthenticationRequest();
		when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenThrow(UsernameNotFoundException.class);
		mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
		.andExpect(status().isBadRequest());
	}

}
