package com.ibm.developer.springbootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private Map<Long, User> users;
	private SequenceGenerator sequenceGenerator;

	public UserService(Map<Long, User> users, SequenceGenerator sequenceGenerator) {
		this.users = users;
		this.sequenceGenerator = sequenceGenerator;
	}

	public User findUser(long userId) {
		userExists(userId);
		return users.get(Long.valueOf(userId));
	}

	public User createUser(User user) {
		validateUser(user);
		Long id = sequenceGenerator.nextVal();
		user.setId(sequenceGenerator.nextVal());
		users.put(id, user);
		return user;
	}

	public User updateUser(long userId, User user) {
		userExists(userId);
		validateUser(user);
		user.setId(userId);
		return users.put(Long.valueOf(userId), user);
	}

	public void deleteUser(long userId) {
		userExists(userId);
		users.remove(Long.valueOf(userId));

	}

	public List<User> findAll() {
		return new ArrayList<User>(users.values());
	}

	private void userExists(long userId) {
		if (!users.containsKey(Long.valueOf(userId))) {
			throw new ClientException(String.format("User id: %d not found!", userId));
		}
	}

	private void validateUser(User user) {
		StringBuilder messageBuilder = new StringBuilder();

		if (StringUtils.isBlank(user.getFirstName())) {
			messageBuilder.append("First name is a required field!");
		}

		if (StringUtils.isBlank(user.getLastName())) {
			if(messageBuilder.length() > 0) {
				messageBuilder.append("\n");
			}
			messageBuilder.append("Last name is a required field!");
		}

		String errorMessage = messageBuilder.toString();

		if (errorMessage.length() > 0) {
			throw new ClientException(errorMessage);
		}
	}
}
