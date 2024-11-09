package com.jisr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.LoginRequestDTO;
import com.jisr.dto.PatientDTO;
import com.jisr.security.JwtResponse;
import com.jisr.security.JwtUtil;
import com.jisr.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/patients")
@RequiredArgsConstructor
public class PatientController {

	private final JwtUtil jwtUtil;
	private final PatientService patientService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody PatientDTO patientDTO) {
		patientService.registerUser(patientDTO);
		return ResponseEntity.ok("Patient registered successfully.");
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
		String emailOrPhone = loginRequestDTO.getEmailOrPhone();
		String password = loginRequestDTO.getPassword();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(emailOrPhone, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateToken(emailOrPhone);
		return ResponseEntity.ok(new JwtResponse(jwt, emailOrPhone));
	}

}
