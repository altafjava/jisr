package com.jisr.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jisr.constant.HealthProviderStatus;
import com.jisr.dto.HealthProviderActionDTO;
import com.jisr.dto.HealthcareProviderSignupRequestDTO;
import com.jisr.entity.HealthcareProvider;
import com.jisr.repository.HealthcareProviderRepository;
import com.jisr.util.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthcareProviderService {

	private final HealthcareProviderRepository repository;
	private final EmailService emailService;

	@Value("${file.upload-dir}")
	private String uploadDir;

	public HealthcareProvider registerProvider(HealthcareProviderSignupRequestDTO dto) throws IOException {
		HealthcareProvider provider = new HealthcareProvider();
		String firstName = dto.getFirstName();
		String fatherName = dto.getFatherName();
		String lastName = dto.getLastName();
		provider.setFirstName(firstName);
		provider.setFatherName(fatherName);
		provider.setLastName(lastName);
		String fullName = String.join(" ", firstName, fatherName == null || fatherName.isBlank() ? "" : fatherName, lastName).trim();
		provider.setFullName(fullName);
		provider.setEmail(dto.getEmail());
		provider.setPhoneNumber(dto.getPhoneNumber());
		provider.setPassword(dto.getPassword());
		provider.setSpecialization(dto.getSpecialization());
		provider.setQualification(dto.getQualification());
		provider.setGender(dto.getGender());
		provider.setDateOfBirth(dto.getDateOfBirth());
		provider.setStatus(HealthProviderStatus.PENDING);
		provider.setPhotoPath(dto.getPhotoPath());
		provider.setCvPath(dto.getCvPath());
		provider.setNoOfMedicalLicenses(dto.getNoOfMedicalLicenses());
		return repository.save(provider);
	}

	public void approveProvider(HealthProviderActionDTO healthProviderActionDTO) throws MessagingException {
		HealthcareProvider provider = repository.findById(healthProviderActionDTO.getId())
				.orElseThrow(() -> new IllegalArgumentException("Health Provider not found"));
		healthProviderActionDTO.getAction();
		HealthProviderStatus status = HealthProviderStatus.fromString(healthProviderActionDTO.getAction());
		provider.setStatus(status);
		repository.save(provider);
		emailService.sendEmail(provider.getEmail(), "Approval", "Your sign-up request has been approved.");
	}
}
