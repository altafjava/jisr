package com.jisr.service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.jisr.entity.Patient;
import com.jisr.entity.Token;
import com.jisr.exception.TokenNotValidException;
import com.jisr.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;

	public String generatePasswordResetToken(Patient patient) {
		String token = UUID.randomUUID().toString();
		Token resetToken = new Token();
		resetToken.setPatient(patient);
		resetToken.setToken(token);
		tokenRepository.save(resetToken);
		return token;
	}

	public Patient validateAndGetPatientByPasswordResetToken(String token) {
		Token resetToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotValidException("Invalid or expired token"));
		if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
			throw new TokenNotValidException("Token has expired.");
		}
		return resetToken.getPatient();
	}
}
