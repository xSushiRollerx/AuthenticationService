package com.xsushirollx.sushibyte.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xsushirollx.sushibyte.authentication.Exception.UserNotAuthorizeException;
import com.xsushirollx.sushibyte.authentication.dao.UserDAO;
import com.xsushirollx.sushibyte.authentication.entities.User;
import com.xsushirollx.sushibyte.authentication.entities.UserDetailsImpl;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userDAO.findByUsername(username);
		if (user==null) {
			throw new UsernameNotFoundException("not found " + username);
		}
		return new UserDetailsImpl(user);
	}
	
	public UserDetails loadAdminByUsername(String username) throws UsernameNotFoundException, UserNotAuthorizeException{
		User user = userDAO.findByUsername(username);
		if (user==null) {
			throw new UsernameNotFoundException("not found " + username);
		}
		if (!user.getEmail().endsWith("@smoothstack.com")) {
			throw new UserNotAuthorizeException(username+" not authorized as admin");
		}
		return new UserDetailsImpl(user);
	}
	
}
