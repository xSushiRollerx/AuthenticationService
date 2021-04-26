package com.xsushirollx.sushibyte.authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xsushirollx.sushibyte.authentication.dto.UserDetailsImpl;
import com.xsushirollx.sushibyte.authentication.entities.User;
import com.xsushirollx.sushibyte.authentication.repositories.UserDAO;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<User> user = Optional.of(userDAO.findByUsername(username));
		user.orElseThrow(() -> new UsernameNotFoundException("not found " + username));
		return new UserDetailsImpl(user.get());
	}
	
}
