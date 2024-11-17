package com.jisr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.entity.User;
import com.jisr.model.ProfileCompletion;
import com.jisr.repository.UserRepository;
import com.jisr.service.ProfileCompletionService;

@RestController
@RequestMapping("/users")
public class ProfileCompletionController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfileCompletionService profileCompletionService;

	@GetMapping("/{id}/profile-completion")
	public ResponseEntity<ProfileCompletion> getProfileCompletion(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for ID: " + id));
		ProfileCompletion completionDto = profileCompletionService.calculateProfileCompletion(user);
		return ResponseEntity.ok(completionDto);
	}
}
