package com.ibm.developer.springbootcamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TestUserService {

	
	@Test
	public void testFindUserExisting() {
		Map<Long, User> users = new HashMap<>();
		users.put(100L, new User(100L, "John", "Doe"));
		UserService service = new UserService(users, null);
		
		User returnedUser = service.findUser(100L);
		
		assertThat(returnedUser).isNotNull();
	}
	
	@Test
	public void testFindUserNotExisting() {
		Map<Long, User> users = new HashMap<>();
		UserService service = new UserService(users, null);
		
		ClientException e = assertThrows(ClientException.class, () -> service.findUser(100L));
		
		assertThat(e.getMessage()).isEqualTo("User id: 100 not found!");
	}

	@Test 
	public void testCreateUserSuccessful() {
		Map<Long, User> users = new HashMap<>();
		UserService service = new UserService(users, new SequenceGenerator());
		
		User newUser = service.createUser(new User("John", "Doe"));
		
		assertThat(newUser.getId()).isGreaterThan(0L);
	}
	
	@Test 
	public void testCreateUserMissingFirstName() {
		Map<Long, User> users = new HashMap<>();
		UserService service = new UserService(users, new SequenceGenerator());
		
		ClientException e = assertThrows(ClientException.class, () -> service.createUser(new User(null, "Doe")));
		
		assertThat(e.getMessage()).isEqualTo("First name is a required field!");
	}
	
	@Test 
	public void testCreateUserMissingLastName() {
		Map<Long, User> users = new HashMap<>();
		UserService service = new UserService(users, new SequenceGenerator());
		
		ClientException e = assertThrows(ClientException.class, () -> service.createUser(new User("John", "")));
		
		assertThat(e.getMessage()).isEqualTo("Last name is a required field!");
	}
	
	@Test 
	public void testCreateUserMissingBothFields() {
		Map<Long, User> users = new HashMap<>();
		UserService service = new UserService(users, new SequenceGenerator());
		
		ClientException e = assertThrows(ClientException.class, () -> service.createUser(new User("", "")));
		
		assertThat(e.getMessage()).isEqualTo("First name is a required field!\nLast name is a required field!");
	}
	
}