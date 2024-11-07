package com.jisr.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.HealthcareProviderSignupRequestDTO;
import com.jisr.service.HealthcareProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/health-provider")
@RequiredArgsConstructor
public class HealthcareProviderController {

	private final HealthcareProviderService providerService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody HealthcareProviderSignupRequestDTO dto) {
		try {
			providerService.registerProvider(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Sign-up request submitted.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
		}
	}
}
