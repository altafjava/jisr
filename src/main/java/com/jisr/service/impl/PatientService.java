package com.jisr.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jisr.dto.PatientProfileDTO;
import com.jisr.dto.UserDTO;
import com.jisr.entity.PatientProfile;
import com.jisr.entity.User;
import com.jisr.event.MeasurementHistoryEvent;
import com.jisr.event.ProfileCompletionEvent;
import com.jisr.event.TransactionalEventPublisher;
import com.jisr.model.PatientProfileData;
import com.jisr.model.PatientProfileResponse;
import com.jisr.model.ProgressProfile;
import com.jisr.repository.PatientProfileRepository;
import com.jisr.repository.UserRepository;
import com.jisr.service.FileStorageService;
import com.jisr.service.ProfileCompletionService;
import com.jisr.util.Calculator;
import com.jisr.util.PatientServiceUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

	private final UserRepository userRepository;
	private final FileStorageService fileStorageService;
	private final ProfileCompletionService profileCompletionService;
	private final PatientProfileRepository patientProfileRepository;
	private final TransactionalEventPublisher transactionalEventPublisher;

	@PreAuthorize("#userId == principal.id")
	public PatientProfileResponse getPatientProfile(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + userId));
		Optional<PatientProfile> patientProfileOptional = patientProfileRepository.findByUserId(userId);
		ProgressProfile progressProfile = profileCompletionService.calculateProfileCompletion(user, patientProfileOptional);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		PatientProfileData patientProfileData = null;
		if (patientProfileOptional.isPresent()) {
			patientProfileData = new PatientProfileData();
			BeanUtils.copyProperties(patientProfileOptional.get(), patientProfileData);
		}
		PatientProfileResponse patientProfileResponse = new PatientProfileResponse();
		patientProfileResponse.setUserProfile(userDTO);
		patientProfileResponse.setPatientProfile(patientProfileData);
		patientProfileResponse.setProgressProfile(progressProfile);
		return patientProfileResponse;
	}

	@Transactional
	@PreAuthorize("#userId == principal.id")
	public void updatePatientProfile(Long userId, PatientProfileDTO patientProfileDTO) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + userId));
		PatientProfile patientProfile = patientProfileRepository.findByUserId(userId).orElse(new PatientProfile());
		patientProfile.setUser(user);
		PatientServiceUtil.updateUser(patientProfileDTO, user);
		PatientServiceUtil.updatePatientHealthDetails(patientProfileDTO, user, patientProfile);
		BigDecimal height = patientProfileDTO.getHeight();
		BigDecimal weight = patientProfileDTO.getWeight();
		if (height != null || weight != null) {
			height = (height != null) ? height : patientProfile.getHeight();
			weight = (weight != null) ? weight : patientProfile.getWeight();
			patientProfile.setHeight(height);
			patientProfile.setWeight(weight);
			if (height != null && weight != null) {
				BigDecimal bmi = Calculator.calculateBMI(height, weight);
				patientProfile.setBmi(bmi);
			}
		}
		patientProfileRepository.save(patientProfile);
		transactionalEventPublisher.publishEventAfterCommit(new MeasurementHistoryEvent(this, patientProfile.getId(), height, weight));
		transactionalEventPublisher.publishEventAfterCommit(ProfileCompletionEvent.forPatient(this, userId, patientProfile.getId()));
	}

	public void uploadPatientFile(Long patientId, MultipartFile file) {
		if (!file.getContentType().matches("application/pdf|image/.*")) {
			throw new IllegalArgumentException("Only PDF or image files are allowed.");
		}
		fileStorageService.storeFile(patientId, file);
	}

	public Long getPatientProfileIdByUserId(Long id) {
		return null;
	}

}
