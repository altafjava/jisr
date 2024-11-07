package com.jisr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.UserDTO;
import com.jisr.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth/register")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
		userService.registerUser(userDTO);
		return ResponseEntity.ok("User registered successfully.");
	}
}
