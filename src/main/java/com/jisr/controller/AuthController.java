package com.jisr.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.LoginDTO;
import com.jisr.dto.PasswordResetDTO;
import com.jisr.dto.RegistrationResponse;
import com.jisr.dto.SignupDTO;
import com.jisr.security.JwtResponse;
import com.jisr.security.JwtUtil;
import com.jisr.security.UserDetailsImpl;
import com.jisr.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JwtUtil jwtUtil;
	private final AuthService authService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDTO signupDTO) {
		RegistrationResponse registrationResponse = authService.registerUser(signupDTO);
	    return ResponseEntity.ok(registrationResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
		String emailOrPhone = loginDTO.getEmailOrPhone();
		String password = loginDTO.getPassword();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(emailOrPhone, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String accessToken = jwtUtil.generateAccessToken(userDetails.getUser(), emailOrPhone);
		String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUser(), emailOrPhone);
		return ResponseEntity.ok(new JwtResponse(emailOrPhone, accessToken, refreshToken));
	}

	@PostMapping("/password-reset/request")
	public ResponseEntity<?> requestPasswordReset(@RequestBody Map<String, String> request) {
		String emailOrPhone = request.get("emailOrPhone");
		authService.sendPasswordResetLink(emailOrPhone);
		return ResponseEntity.ok("Password reset link has been sent.");
	}

	@PostMapping("/password-reset")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetDTO resetDTO) {
		authService.resetPassword(resetDTO);
		return ResponseEntity.ok("Password has been reset successfully.");
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> requestBody) {
		String refreshToken = requestBody.get("refreshToken");
		try {
			String username = jwtUtil.getSubjectFromToken(refreshToken);
			String newAccessToken = authService.refreshAccessToken(refreshToken);
			return ResponseEntity.ok(new JwtResponse(username, newAccessToken, refreshToken));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
		}
	}

	/**
	 * Need to implement the token Blacklist using Redis
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok("Logged out successfully.");
	}

}
