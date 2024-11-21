package com.jisr.util;

import com.jisr.constant.ProviderStatus;
import com.jisr.dto.HealthcareProviderDTO;
import com.jisr.entity.HealthcareProvider;
import com.jisr.entity.User;
import com.jisr.validator.Gender;

public class HealthcareProviderServiceUtil {

	public static String deriveFullName(String firstName, String fatherName, String lastName) {
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(firstName);
		if (fatherName != null && !fatherName.isEmpty()) {
			nameBuilder.append(" ").append(fatherName);
		}
		nameBuilder.append(" ").append(lastName);
		return nameBuilder.toString().trim();
	}

	public static void updateUser(HealthcareProviderDTO healthcareProviderDTO, User user) {
		updateNames(healthcareProviderDTO, user);
		if (healthcareProviderDTO.getEmail() != null) {
			user.setEmail(healthcareProviderDTO.getEmail());
		}
		if (healthcareProviderDTO.getPhoneNumber() != null) {
			user.setPhoneNumber(healthcareProviderDTO.getPhoneNumber());
		}
	}

	public static void updateNames(HealthcareProviderDTO healthcareProviderDTO, User user) {
		String firstName = healthcareProviderDTO.getFirstName();
		String fatherName = healthcareProviderDTO.getFatherName();
		String lastName = healthcareProviderDTO.getLastName();
		boolean isUpdated = false;
		if (firstName != null && !firstName.equals(user.getFirstName())) {
			user.setFirstName(firstName);
			isUpdated = true;
		}
		if (fatherName != null && !fatherName.equals(user.getFatherName())) {
			user.setFatherName(fatherName);
			isUpdated = true;
		}
		if (lastName != null && !lastName.equals(user.getLastName())) {
			user.setLastName(lastName);
			isUpdated = true;
		}
		if (isUpdated) {
			String fullName = deriveFullName(
					firstName != null ? firstName : user.getFirstName(),
					fatherName != null ? fatherName : user.getFatherName(),
					lastName != null ? lastName : user.getLastName());
			user.setFullName(fullName);
		}
	}

	public static HealthcareProvider updateHealthcareProviderDetails(HealthcareProviderDTO healthcareProviderDTO, User user,
			HealthcareProvider healthcareProvider) {
		healthcareProvider.setUser(user);
		if (healthcareProviderDTO.getGender() != null) {
			healthcareProvider.setGender(Gender.fromString(healthcareProviderDTO.getGender()));
		}
		if (healthcareProviderDTO.getDateOfBirth() != null) {
			healthcareProvider.setDateOfBirth(healthcareProviderDTO.getDateOfBirth());
		}
		if (healthcareProviderDTO.getSpecialization() != null) {
			healthcareProvider.setSpecialization(healthcareProviderDTO.getSpecialization());
		}
		if (healthcareProviderDTO.getQualification() != null) {
			healthcareProvider.setQualification(healthcareProviderDTO.getQualification());
		}
		if (healthcareProviderDTO.getExperience() != null) {
			healthcareProvider.setExperience(healthcareProviderDTO.getExperience());
		}
		if (healthcareProviderDTO.getNoOfMedicalLicenses() != null) {
			healthcareProvider.setNoOfMedicalLicenses(healthcareProviderDTO.getNoOfMedicalLicenses());
		}
		if (healthcareProviderDTO.getPersonalPhotoUrl() != null) {
			healthcareProvider.setPersonalPhotoUrl(healthcareProviderDTO.getPersonalPhotoUrl());
		}
		if (healthcareProviderDTO.getCvUrl() != null) {
			healthcareProvider.setCvUrl(healthcareProviderDTO.getCvUrl());
		}
		if (healthcareProvider.getStatus() == null) {
			healthcareProvider.setStatus(ProviderStatus.DRAFTED);
		}
		return healthcareProvider;
	}

}
