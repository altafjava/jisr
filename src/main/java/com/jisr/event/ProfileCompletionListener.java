package com.jisr.event;

import java.util.Optional;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.jisr.entity.HealthcareProvider;
import com.jisr.entity.PatientProfile;
import com.jisr.entity.User;
import com.jisr.model.ProgressProfile;
import com.jisr.repository.HealthcareProviderRepository;
import com.jisr.repository.PatientProfileRepository;
import com.jisr.repository.UserRepository;
import com.jisr.service.ProfileCompletionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileCompletionListener {

	private final UserRepository userRepository;
	private final PatientProfileRepository patientProfileRepository;
	private final ProfileCompletionService profileCompletionService;
	private final HealthcareProviderRepository healthcareProviderRepository;

	@Async
	@EventListener
	@Transactional
	public void handleProfileCompletionEvent(ProfileCompletionEvent event) {
		log.info("Received ProfileCompletionEvent for userId: {} and type: {}", event.getUserId(), event.getEventType());
		try {
			switch (event.getEventType()) {
			case USER -> handleUserEvent(event);
			case ADMIN -> handleAdminEvent(event);
			case PATIENT -> handlePatientEvent(event);
			case HEALTHCARE_PROVIDER -> handleHealthcareProviderEvent(event);
			default -> log.warn("Unknown event type: {}", event.getEventType());
			}
		} catch (Exception ex) {
			log.error("Error handling ProfileCompletionEvent for userId: {}", event.getUserId(), ex);
			throw ex; // Optionally propagate or handle the error gracefully
		}
	}

	private void handleUserEvent(ProfileCompletionEvent event) {
		processUserProfile(event.getUserId(), Optional.empty());
	}

	private void handleAdminEvent(ProfileCompletionEvent event) {
		processUserProfile(event.getUserId(), Optional.empty());
	}

	private void handlePatientEvent(ProfileCompletionEvent event) {
		Long userId = event.getUserId();
		Optional<PatientProfile> patientProfile = patientProfileRepository.findByUserId(userId);
		processUserProfile(userId, patientProfile);
	}

	private void handleHealthcareProviderEvent(ProfileCompletionEvent event) {
		Long userId = event.getUserId();
		Optional<HealthcareProvider> healthcareProvider = healthcareProviderRepository.findByUserId(userId);
		processUserProfile(userId, healthcareProvider);
	}

	/**
	 * Reusable method to process user profiles and calculate profile completion.
	 * 
	 * @param userId       The user ID.
	 * @param optionalData Optional profile data (patient/healthcare provider).
	 */
	private <T> void processUserProfile(Long userId, Optional<T> optionalData) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> {
					log.error("User not found for ID: {}", userId);
					return new IllegalArgumentException("User not found for ID: " + userId);
				});
		log.info("Calculating profile completion for userId: {}", userId);
		ProgressProfile progressProfile = profileCompletionService.calculateProfileCompletion(user, optionalData);
		user.setProfileCompletion(progressProfile.getCompletionPercentage());
		userRepository.save(user);
		log.info("Updated profile completion for userId: {} to {}%", userId, progressProfile.getCompletionPercentage());
	}

}
