package com.ibm.developer.springbootcamp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloSpringController {

	@GetMapping
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/{message}")
	public String helloMessage(@PathVariable String message) {
		return String.format("Hello %s!", message);
	}
	
	@GetMapping("/name")
	public String helloQueryMessage(@RequestParam String firstName,@RequestParam String lastName ) {
		return String.format("Hello %s %s!", firstName, lastName);
	}
	
	@GetMapping("/header")
	public String welcomeUser(@RequestHeader String user) {
		return String.format("Welcome %s!", user);
	}
}
