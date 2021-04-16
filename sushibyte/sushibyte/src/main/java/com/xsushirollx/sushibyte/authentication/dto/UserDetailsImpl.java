package com.xsushirollx.sushibyte.authentication.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.xsushirollx.sushibyte.authentication.entities.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -5599081972091873478L;
	private String username;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authority;
	private Integer id;
	
	public UserDetailsImpl(User user){
		username = user.getUsername();
		password = user.getPassword();
		isActive = user.isActive();
		switch(user.getUserRole()) {
		case 1:
			authority.add(new SimpleGrantedAuthority("ADMIN"));
			break;
		case 2:
			authority.add(new SimpleGrantedAuthority("CUSTOMER"));
			break;
		case 3:
			authority.add(new SimpleGrantedAuthority("DRIVER"));
			break;
		}
		setId(user.getId());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
