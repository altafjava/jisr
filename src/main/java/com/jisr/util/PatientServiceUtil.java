package com.jisr.util;

import com.jisr.dto.PatientProfileDTO;
import com.jisr.entity.PatientProfile;
import com.jisr.entity.User;
import com.jisr.validator.Gender;

public class PatientServiceUtil {

	public static String deriveFullName(String firstName, String fatherName, String lastName) {
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(firstName);
		if (fatherName != null && !fatherName.isEmpty()) {
			nameBuilder.append(" ").append(fatherName);
		}
		nameBuilder.append(" ").append(lastName);
		return nameBuilder.toString().trim();
	}

//	public static PatientProfileDTO buildPatientProfileDTO(Patient patient, PatientHealthDetails patientHealthDetails) {
//		PatientProfileDTO patientProfileDTO = new PatientProfileDTO();
//		patientProfileDTO.setUsername(patient.getUsername());
//		patientProfileDTO.setFirstName(patient.getFirstName());
//		patientProfileDTO.setFatherName(patient.getFatherName());
//		patientProfileDTO.setLastName(patient.getLastName());
//		patientProfileDTO.setFullName(patient.getFullName());
//		patientProfileDTO.setEmail(patient.getEmail());
//		patientProfileDTO.setPhoneNumber(patient.getPhoneNumber());
//		patientProfileDTO.setRelationship(patient.getRelationship());
//		patientProfileDTO.setRole(patient.getRole());
//		patientProfileDTO.setFirstLogin(patient.isFirstLogin());
//		patientProfileDTO.setCancerType(patientHealthDetails.getCancerType());
//		patientProfileDTO.setCancerTreatment(patientHealthDetails.getCancerTreatment());
//		patientProfileDTO.setMedicinesAndDoses(patientHealthDetails.getMedicinesAndDoses());
//		patientProfileDTO.setChemotherapyHistory(patientHealthDetails.getChemotherapyHistory());
//		patientProfileDTO.setHasCentralCatheter(patientHealthDetails.getHasCentralCatheter());
//		patientProfileDTO.setCatheterType(patientHealthDetails.getCatheterType());
//		patientProfileDTO.setHeight(patientHealthDetails.getHeight());
//		patientProfileDTO.setWeight(patientHealthDetails.getWeight());
//		patientProfileDTO.setBmi(patientHealthDetails.getBmi());
//		patientProfileDTO.setAge(patientHealthDetails.getAge());
//		patientProfileDTO.setDateOfBirth(patientHealthDetails.getDateOfBirth());
//		patientProfileDTO.setGender(patientHealthDetails.getGender());
//		patientProfileDTO.setHealthcareRegion(patientHealthDetails.getHealthcareRegion());
//		return patientProfileDTO;
//	}

	public static void updateUser(PatientProfileDTO patientProfileDTO, User user) {
		updateNames(patientProfileDTO, user);
		if (patientProfileDTO.getUsername() != null) {
			user.setUsername(patientProfileDTO.getUsername());
		}
		if (patientProfileDTO.getEmail() != null) {
			user.setEmail(patientProfileDTO.getEmail());
		}
		if (patientProfileDTO.getPhoneNumber() != null) {
			user.setPhoneNumber(patientProfileDTO.getPhoneNumber());
		}
	}

	public static void updateNames(PatientProfileDTO patientProfileDTO, User user) {
		String firstName = patientProfileDTO.getFirstName();
		String fatherName = patientProfileDTO.getFatherName();
		String lastName = patientProfileDTO.getLastName();
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

	public static PatientProfile updatePatientHealthDetails(PatientProfileDTO patientProfileDTO, User user, PatientProfile patientProfile) {
		patientProfile.setUser(user);
		if (patientProfileDTO.getGender() != null) {
		    patientProfile.setGender(Gender.fromString(patientProfileDTO.getGender()));
		}
		if (patientProfileDTO.getDateOfBirth() != null) {
			patientProfile.setDateOfBirth(patientProfileDTO.getDateOfBirth());
		}
		if (patientProfileDTO.getRelationship() != null) {
			patientProfile.setRelationship(patientProfileDTO.getRelationship());
		}
		if (patientProfileDTO.getCancerType() != null) {
			patientProfile.setCancerType(patientProfileDTO.getCancerType());
		}
		if (patientProfileDTO.getTreatmentType() != null) {
			patientProfile.setTreatmentType(patientProfileDTO.getTreatmentType());
		}
		if (patientProfileDTO.getMedicinesAndDoses() != null) {
			patientProfile.setMedicinesAndDoses(patientProfileDTO.getMedicinesAndDoses());
		}
		if (patientProfileDTO.getChemotherapyHistory() != null) {
			patientProfile.setChemotherapyHistory(patientProfileDTO.getChemotherapyHistory());
		}
		if (patientProfileDTO.getHasCentralCatheter() != null) {
			patientProfile.setHasCentralCatheter(patientProfileDTO.getHasCentralCatheter());
		}
		if (patientProfileDTO.getCentralCatheterType() != null) {
			patientProfile.setCentralCatheterType(patientProfileDTO.getCentralCatheterType());
		}
		if (patientProfileDTO.getHealthcareRegion() != null) {
			patientProfile.setHealthcareRegion(patientProfileDTO.getHealthcareRegion());
		}
		return patientProfile;
	}

//	public static void updateHeightWeightAndBMI(PatientProfileDTO patientProfileDTO, PatientProfile patientProfile) {
//		boolean heightUpdated = patientProfileDTO.getHeight() != null;
//		boolean weightUpdated = patientProfileDTO.getWeight() != null;
//		if (heightUpdated) {
//			patientProfile.setHeight(patientProfileDTO.getHeight());
//		}
//		if (weightUpdated) {
//			patientProfile.setWeight(patientProfileDTO.getWeight());
//		}
//		if (heightUpdated || weightUpdated) {
//			BigDecimal height = heightUpdated ? patientProfileDTO.getHeight() : patientProfile.getHeight();
//			BigDecimal weight = weightUpdated ? patientProfileDTO.getWeight() : patientProfile.getWeight();
//			if (height != null && weight != null && height.compareTo(BigDecimal.ZERO) > 0) {
//				BigDecimal bmi = patientProfile.calculateBMI(height, weight);
//				patientProfile.setBmi(bmi);
//			}
//		}
//	}

}
