package com.jisr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jisr.dto.UserDTO;
import com.jisr.model.User;
import com.jisr.repository.UserRepository;
import com.jisr.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User registerUser(UserDTO userDTO) {
		User user = new User();
	    user.setUsername(userDTO.getUsername());
	    user.setFirstName(userDTO.getFirstName());
	    user.setFatherName(userDTO.getFatherName());
	    user.setLastName(userDTO.getLastName());
	    user.setEmail(userDTO.getEmail());
	    user.setPhoneNumber(userDTO.getPhoneNumber());
	    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	    user.setRole(userDTO.getRole()); // Directly set Role enum
	    user.setRelationship(userDTO.getRelationship());
	    return userRepository.save(user);
	}
}
