package com.xsushirollx.sushibyte.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsushirollx.sushibyte.authentication.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.username=?1")
	public User findByUsername(String username);
}
