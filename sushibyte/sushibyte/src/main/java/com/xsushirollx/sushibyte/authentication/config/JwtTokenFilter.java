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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.xsushirollx.sushibyte.authentication.dto.UserDetailsImpl;
import com.xsushirollx.sushibyte.authentication.entities.User;
import com.xsushirollx.sushibyte.authentication.repositories.UserDAO;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	UserDAO userDAO;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Get authorization header and validate
		if (header==null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
		
		String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

		User user = userDAO.findById(jwtTokenUtil.getIdFromToken(token)).orElse(null);
		UserDetailsImpl userDetails = null;
		if (user!=null) {
			userDetails = new UserDetailsImpl(user);
		}
		
		UsernamePasswordAuthenticationToken	authentication = 
				new UsernamePasswordAuthenticationToken(
				userDetails, null,
				userDetails == null ?
                Collections.emptyList() : userDetails.getAuthorities()
        );
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
	}

}
