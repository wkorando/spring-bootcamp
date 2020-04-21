package com.ibm.developer.springbootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private List<User> users = new ArrayList<>();
	private static final Random ID_GENERATOR = new Random();

	public User findUser(long userId) {
		for(User user : users) {
			if(user.getId().equals(Long.valueOf(userId))) {
				return user;
			}
		}
		throw new NotFoundException();
		//throw new ClientException(String.format("User id: %d not found!", user.getId()));
	}
	
	public User createUser(User user) {
		user.setId(ID_GENERATOR.nextLong());
		users.add(user);
		return user;
	}

	public User updateUser(long userId, User user) {
		user.setId(userId);
		// User equals looks only at the id field which is why this works despite
		// looking weird
		if (users.contains(user)) {
			users.remove(user);
			users.add(user);
			return user;
		}
		throw new ClientException(String.format("User id: %d not found!", user.getId()));
	}

	public void deleteUser(long userId) {
		Optional<User> foundUser = users.stream().filter(u -> u.getId() == userId).findFirst();
		if (foundUser.isPresent()) {
			users.remove(foundUser.get());
			return;
		}
		throw new ClientException(String.format("User id: %d not found!", userId));
	}

	public List<User> findAll() {
		return users;
	}
}
