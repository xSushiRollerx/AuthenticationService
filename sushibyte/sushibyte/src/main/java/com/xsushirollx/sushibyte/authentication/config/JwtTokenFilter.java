package com.xsushirollx.sushibyte.authentication.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.xsushirollx.sushibyte.authentication.dto.UserDetailsImpl;
import com.xsushirollx.sushibyte.authentication.entities.User;
import com.xsushirollx.sushibyte.authentication.repositories.UserDAO;
import com.xsushirollx.sushibyte.authentication.services.UserDetailServiceImpl;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserDetailServiceImpl userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		
		chain.doFilter(request, response);
	}

}
