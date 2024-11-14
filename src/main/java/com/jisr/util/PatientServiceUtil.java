package com.jisr.util;

import com.jisr.dto.PatientProfileDTO;
import com.jisr.entity.Patient;
import com.jisr.entity.PatientHealthDetails;

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

	public static PatientProfileDTO buildPatientProfileDTO(Patient patient, PatientHealthDetails patientHealthDetails) {
		PatientProfileDTO patientProfileDTO = new PatientProfileDTO();
		patientProfileDTO.setUsername(patient.getUsername());
		patientProfileDTO.setFirstName(patient.getFirstName());
		patientProfileDTO.setFatherName(patient.getFatherName());
		patientProfileDTO.setLastName(patient.getLastName());
		patientProfileDTO.setFullName(patient.getFullName());
		patientProfileDTO.setEmail(patient.getEmail());
		patientProfileDTO.setPhoneNumber(patient.getPhoneNumber());
		patientProfileDTO.setRelationship(patient.getRelationship());
		patientProfileDTO.setRole(patient.getRole());
		patientProfileDTO.setFirstLogin(patient.isFirstLogin());
		patientProfileDTO.setCancerType(patientHealthDetails.getCancerType());
		patientProfileDTO.setCancerTreatment(patientHealthDetails.getCancerTreatment());
		patientProfileDTO.setMedicinesAndDoses(patientHealthDetails.getMedicinesAndDoses());
		patientProfileDTO.setChemotherapyHistory(patientHealthDetails.getChemotherapyHistory());
		patientProfileDTO.setHasCentralCatheter(patientHealthDetails.getHasCentralCatheter());
		patientProfileDTO.setCatheterType(patientHealthDetails.getCatheterType());
		patientProfileDTO.setHeight(patientHealthDetails.getHeight());
		patientProfileDTO.setWeight(patientHealthDetails.getWeight());
		patientProfileDTO.setBmi(patientHealthDetails.getBmi());
		patientProfileDTO.setAge(patientHealthDetails.getAge());
		patientProfileDTO.setDateOfBirth(patientHealthDetails.getDateOfBirth());
		patientProfileDTO.setGender(patientHealthDetails.getGender());
		patientProfileDTO.setHealthcareRegion(patientHealthDetails.getHealthcareRegion());
		return patientProfileDTO;
	}

	public static void updatePatient(PatientProfileDTO patientProfileDTO, Patient patient) {
		updateNames(patientProfileDTO, patient);
		if (patientProfileDTO.getUsername() != null) {
			patient.setUsername(patientProfileDTO.getUsername());
		}
		if (patientProfileDTO.getEmail() != null) {
			patient.setEmail(patientProfileDTO.getEmail());
		}
		if (patientProfileDTO.getPhoneNumber() != null) {
			patient.setPhoneNumber(patientProfileDTO.getPhoneNumber());
		}
		if (patientProfileDTO.getRole() != null) {
			patient.setRole(patientProfileDTO.getRole());
		}
		if (patientProfileDTO.getFirstLogin() != null) {
			patient.setFirstLogin(patientProfileDTO.getFirstLogin());
		}
	}

	public static void updateNames(PatientProfileDTO patientProfileDTO, Patient patient) {
	    String firstName = patientProfileDTO.getFirstName();
	    String fatherName = patientProfileDTO.getFatherName();
	    String lastName = patientProfileDTO.getLastName();
	    boolean isUpdated = false;
	    if (firstName != null && !firstName.equals(patient.getFirstName())) {
	        patient.setFirstName(firstName);
	        isUpdated = true;
	    }
	    if (fatherName != null && !fatherName.equals(patient.getFatherName())) {
	        patient.setFatherName(fatherName);
	        isUpdated = true;
	    }
	    if (lastName != null && !lastName.equals(patient.getLastName())) {
	        patient.setLastName(lastName);
	        isUpdated = true;
	    }
	    if (isUpdated) {
	        String fullName = deriveFullName(firstName != null ? firstName : patient.getFirstName(),
	                                         fatherName != null ? fatherName : patient.getFatherName(),
	                                         lastName != null ? lastName : patient.getLastName());
	        patient.setFullName(fullName);
	    }
	}


	public static PatientHealthDetails updatePatientHealthDetails(PatientProfileDTO patientProfileDTO, Patient patient) {
		PatientHealthDetails patientHealthDetails = patient.getPatientHealthDetails();
		if (patientHealthDetails == null) {
			patientHealthDetails = new PatientHealthDetails();
			patientHealthDetails.setPatient(patient);
		}
		if (patientProfileDTO.getCancerType() != null) {
			patientHealthDetails.setCancerType(patientProfileDTO.getCancerType());
		}
		if (patientProfileDTO.getCancerTreatment() != null) {
			patientHealthDetails.setCancerTreatment(patientProfileDTO.getCancerTreatment());
		}
		if (patientProfileDTO.getMedicinesAndDoses() != null) {
			patientHealthDetails.setMedicinesAndDoses(patientProfileDTO.getMedicinesAndDoses());
		}
		if (patientProfileDTO.getChemotherapyHistory() != null) {
			patientHealthDetails.setChemotherapyHistory(patientProfileDTO.getChemotherapyHistory());
		}
		if (patientProfileDTO.getHasCentralCatheter() != null) {
			patientHealthDetails.setHasCentralCatheter(patientProfileDTO.getHasCentralCatheter());
		}
		if (patientProfileDTO.getCatheterType() != null) {
			patientHealthDetails.setCatheterType(patientProfileDTO.getCatheterType());
		}
		if (patientProfileDTO.getGender() != null) {
			patientHealthDetails.setGender(patientProfileDTO.getGender());
		}
		if (patientProfileDTO.getHealthcareRegion() != null) {
			patientHealthDetails.setHealthcareRegion(patientProfileDTO.getHealthcareRegion());
		}
		return patientHealthDetails;
	}

	public static void updateHeightWeightAndBMI(PatientProfileDTO patientProfileDTO, PatientHealthDetails patientHealthDetails) {
		boolean heightUpdated = patientProfileDTO.getHeight() != null;
		boolean weightUpdated = patientProfileDTO.getWeight() != null;
		if (heightUpdated) {
			patientHealthDetails.setHeight(patientProfileDTO.getHeight());
		}
		if (weightUpdated) {
			patientHealthDetails.setWeight(patientProfileDTO.getWeight());
		}
		if (heightUpdated || weightUpdated) {
			Integer height = heightUpdated ? patientProfileDTO.getHeight() : patientHealthDetails.getHeight();
			Integer weight = weightUpdated ? patientProfileDTO.getWeight() : patientHealthDetails.getWeight();
			if (height != null && weight != null && height > 0) {
				Double bmi = patientHealthDetails.calculateBMI(height, weight);
				patientHealthDetails.setBmi(bmi);
			}
		}
	}

	public static void updateDateOfBirthAndAge(PatientProfileDTO patientProfileDTO, PatientHealthDetails patientHealthDetails) {
		if (patientProfileDTO.getDateOfBirth() != null) {
			patientHealthDetails.setDateOfBirth(patientProfileDTO.getDateOfBirth());
			Integer age = patientHealthDetails.calculateAge(patientProfileDTO.getDateOfBirth());
			patientHealthDetails.setAge(age);
		}
	}

}
