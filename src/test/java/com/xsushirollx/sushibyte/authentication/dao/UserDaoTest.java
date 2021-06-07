/**
 * 
 */
package com.xsushirollx.sushibyte.authentication.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.Instant;

import javax.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.xsushirollx.sushibyte.authentication.dao.UserDAO;
import com.xsushirollx.sushibyte.authentication.entities.User;

/**
 * @author dyltr
 * Test user crud operations
 */
@SpringBootTest
class UserDaoTest {
	@Autowired
	private UserDAO u1;

	@Test
	@Transactional
	@Rollback(true)
	void findByUsernameTest() {
		User test1 = new User();
		test1.setFirstName("first");
		test1.setLastName("last");
		test1.setPhone("phone");
		test1.setEmail("test1");
		test1.setUsername("test1");
		test1.setPassword("password");
		test1.setCreatedAt(Timestamp.from(Instant.now()));
		test1 = u1.save(test1);
		int id = test1.getId();
		test1 = u1.findByUsername("test1");
		assertNotNull(test1);
		assertEquals(test1.getId(), id);
		assertEquals(test1.getFirstName(), "first");
		assertEquals(test1.getLastName(), "last");
		assertEquals(test1.getEmail(), "test1");
		assertEquals(test1.getUsername(), "test1");
		assertEquals(test1.getPassword(), "password");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void findByEmailTest() {
		User test1 = new User();
		test1.setFirstName("first");
		test1.setLastName("last");
		test1.setPhone("phone");
		test1.setEmail("test1");
		test1.setUsername("test1");
		test1.setPassword("password");
		test1.setCreatedAt(Timestamp.from(Instant.now()));
		test1 = u1.save(test1);
		int id = test1.getId();
		test1 = u1.findByEmail("test1");
		assertNotNull(test1);
		assertEquals(test1.getId(), id);
		assertEquals(test1.getFirstName(), "first");
		assertEquals(test1.getLastName(), "last");
		assertEquals(test1.getEmail(), "test1");
		assertEquals(test1.getUsername(), "test1");
		assertEquals(test1.getPassword(), "password");
	}

}
