package com.jisr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.UserDTO;
import com.jisr.model.User;
import com.jisr.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
		User user = userService.registerUser(userDTO);
		return ResponseEntity.ok(user);
	}
}
