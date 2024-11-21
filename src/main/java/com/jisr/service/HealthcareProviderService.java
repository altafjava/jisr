package com.jisr.service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jisr.constant.FileCategory;
import com.jisr.constant.ProviderStatus;
import com.jisr.dto.HealthProviderActionDTO;
import com.jisr.dto.HealthcareProviderDTO;
import com.jisr.dto.UserDTO;
import com.jisr.entity.HealthcareProvider;
import com.jisr.entity.User;
import com.jisr.event.ProfileCompletionEvent;
import com.jisr.event.TransactionalEventPublisher;
import com.jisr.exception.ProfileNotCompletedException;
import com.jisr.model.HealthcareProviderData;
import com.jisr.model.HealthcareProviderProfileResponse;
import com.jisr.model.ProgressProfile;
import com.jisr.repository.HealthcareProviderRepository;
import com.jisr.repository.UserRepository;
import com.jisr.util.HealthcareProviderServiceUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthcareProviderService {

	private final UserRepository userRepository;
	private final NotificationService notificationService;
	private final ProfileCompletionService profileCompletionService;
	private final TransactionalEventPublisher transactionalEventPublisher;
	private final HealthcareProviderRepository healthcareProviderRepository;

	@Value("${file.upload-dir}")
	private String uploadDir;

	public HealthcareProviderProfileResponse getHealthcareProviderProfile(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + userId));
		Optional<HealthcareProvider> healthcareProviderOptional = healthcareProviderRepository.findByUserId(userId);
		ProgressProfile progressProfile = profileCompletionService.calculateProfileCompletion(user, healthcareProviderOptional);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		HealthcareProviderData healthcareProviderData = null;
		if (healthcareProviderOptional.isPresent()) {
			healthcareProviderData = new HealthcareProviderData();
			BeanUtils.copyProperties(healthcareProviderOptional.get(), healthcareProviderData);
		}
		HealthcareProviderProfileResponse healthcareProviderProfileResponse = new HealthcareProviderProfileResponse();
		healthcareProviderProfileResponse.setUserProfile(userDTO);
		healthcareProviderProfileResponse.setProviderProfile(healthcareProviderData);
		healthcareProviderProfileResponse.setProgressProfile(progressProfile);
		return healthcareProviderProfileResponse;
	}

	@Transactional
	public void updateHealthcareProvider(Long userId, HealthcareProviderDTO healthcareProviderDTO) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + userId));
		HealthcareProvider healthcareProvider = healthcareProviderRepository.findByUserId(userId).orElse(new HealthcareProvider());
		healthcareProvider.setUser(user);
		HealthcareProviderServiceUtil.updateUser(healthcareProviderDTO, user);
		HealthcareProviderServiceUtil.updateHealthcareProviderDetails(healthcareProviderDTO, user, healthcareProvider);
		healthcareProviderRepository.save(healthcareProvider);
		transactionalEventPublisher.publishEventAfterCommit(ProfileCompletionEvent.forHealthcareProvider(this, userId, healthcareProvider.getId()));
	}

	/**
	 * Need to implement Store the file in S3 or any other cloud storage
	 */
	public String uploadFile(Long id, MultipartFile file, FileCategory fileCategory) {
		System.out.println(fileCategory);
		return file.getOriginalFilename();
	}

	public void handleProviderAction(HealthProviderActionDTO healthProviderActionDTO) {
		Long userId = healthProviderActionDTO.getUserId();
		HealthcareProvider healthcareProvider = healthcareProviderRepository.findByUserId(userId)
				.orElseThrow(() -> new IllegalArgumentException("HealthcareProvider not found with userId: " + userId));
		User user = healthcareProvider.getUser();
		if (user.getProfileCompletion() == 100) {
			ProviderStatus healthProviderStatus = ProviderStatus.fromString(healthProviderActionDTO.getAction());
			healthcareProvider.setStatus(healthProviderStatus);
			healthcareProviderRepository.save(healthcareProvider);
			notificationService.notifyHealthcareProviderAccountStatus(healthcareProvider, healthProviderStatus);
		} else {
			throw new ProfileNotCompletedException(user, "The HealthcareProvider profile has not been completed 100%");
		}
	}

}
