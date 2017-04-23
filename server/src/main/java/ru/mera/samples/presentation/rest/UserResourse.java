package ru.mera.samples.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.mera.samples.application.dto.UserDTO;
import ru.mera.samples.application.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResourse {

	
	@Autowired
	UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public UserDTO getUser(@PathVariable("userId") Long userId) {
		UserDTO userDTO =userService.read(userId);
		return userDTO;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDTO> getUser() {
		List<UserDTO> userDTO =userService.readAll();
		return userDTO;
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public UserDTO addUser(@RequestBody UserDTO user) {
		userService.create(user);		
		return user;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String updateUser(@RequestBody UserDTO user) {
		userService.update(user);
		return "This is our message PUT request";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("userId") Long userId) {
		userService.delete(userId);
		return "This is our message DELETE request" + userId;
	}
}
