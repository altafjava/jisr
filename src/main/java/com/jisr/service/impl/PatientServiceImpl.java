package com.jisr.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jisr.dto.PatientHealthDetailsDTO;
import com.jisr.dto.PatientDTO;
import com.jisr.entity.Patient;
import com.jisr.entity.PatientHealthDetails;
import com.jisr.repository.PatientHealthDetailsRepository;
import com.jisr.repository.PatientRepository;
import com.jisr.service.PatientService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final PasswordEncoder passwordEncoder;
	private final PatientHealthDetailsRepository patientHealthDetailsRepository;

	@Override
	public Patient registerUser(PatientDTO userDTO) {
		Patient user = new Patient();
		user.setUsername(userDTO.getUsername());
		user.setFirstName(userDTO.getFirstName());
		user.setFatherName(userDTO.getFatherName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getRole()); // Directly set Role enum
		user.setRelationship(userDTO.getRelationship());
		return patientRepository.save(user);
	}

	@Transactional
	public void savePatientHealthDetails(Long userId, PatientHealthDetailsDTO info) {
		Patient patient = patientRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
		PatientHealthDetails patientHealthDetails = patientHealthDetailsRepository.findByPatient(patient).orElse(new PatientHealthDetails());
		patientHealthDetails.setCancerType(info.getCancerType());
		patientHealthDetails.setCancerTreatment(info.getCancerTreatment());
		patientHealthDetails.setMedicinesAndDoses(info.getMedicinesAndDoses());
		patientHealthDetails.setChemotherapyHistory(info.getChemotherapyHistory());
		patientHealthDetails.setHasCentralCatheter(info.getHasCentralCatheter());
		patientHealthDetails.setCatheterType(info.getHasCentralCatheter() ? info.getCatheterType() : null);
		patientHealthDetails.setHeight(info.getHeight());
		patientHealthDetails.setWeight(info.getWeight());
		patientHealthDetails.setDateOfBirth(info.getDateOfBirth());
		patientHealthDetails.setGender(info.getGender());
		patientHealthDetails.setHealthcareRegion(info.getHealthcareRegion());
		patientHealthDetails.setPatient(patient); // Associate with the patient
		patientHealthDetails.calculateDerivedData();
		patientHealthDetailsRepository.save(patientHealthDetails);
	}
}
