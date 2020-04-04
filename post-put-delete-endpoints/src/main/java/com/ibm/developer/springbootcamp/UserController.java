package com.ibm.developer.springbootcamp;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> findAll() {
		return service.findAll();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = service.createUser(user);
		return ResponseEntity.created(URI.create(String.format("/api/v1/users/%d", createdUser.getId())))
				.body(createdUser);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable long userId, @RequestBody User user) {
		return service.updateUser(userId, user);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
		service.deleteUser(userId);
		return ResponseEntity.ok().build();
	}

	@ExceptionHandler(ClientException.class)
	public ResponseEntity<String> clientError(ClientException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
