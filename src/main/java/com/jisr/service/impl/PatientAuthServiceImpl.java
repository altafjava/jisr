package com.jisr.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jisr.dto.PasswordResetDTO;
import com.jisr.dto.PatientAuthDTO;
import com.jisr.dto.PatientHealthDetailsDTO;
import com.jisr.entity.Patient;
import com.jisr.entity.PatientHealthDetails;
import com.jisr.repository.PatientHealthDetailsRepository;
import com.jisr.repository.PatientRepository;
import com.jisr.service.PatientAuthService;
import com.jisr.service.SmsService;
import com.jisr.service.TokenService;
import com.jisr.util.EmailService;
import com.jisr.util.PatientServiceUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientAuthServiceImpl implements PatientAuthService {

	private final PatientRepository patientRepository;
	private final PasswordEncoder passwordEncoder;
	private final PatientHealthDetailsRepository patientHealthDetailsRepository;
	private final EmailService emailService;
	private final TokenService tokenService;
	private final SmsService smsService;
	@Value("${password-reset-request}")
	private String passwordResetRequestUrl;

	@Override
	public Patient registerUser(PatientAuthDTO patientAuthDTO) {
		Patient user = new Patient();
		user.setUsername(patientAuthDTO.getUsername());
		String firstName = patientAuthDTO.getFirstName();
		user.setFirstName(firstName);
		String fatherName = patientAuthDTO.getFatherName();
		user.setFatherName(fatherName);
		String lastName = patientAuthDTO.getLastName();
		user.setLastName(lastName);
		user.setFullName(PatientServiceUtil.deriveFullName(firstName, fatherName, lastName));
		user.setEmail(patientAuthDTO.getEmail());
		user.setPhoneNumber(patientAuthDTO.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(patientAuthDTO.getPassword()));
		user.setRole(patientAuthDTO.getRole());
		user.setRelationship(patientAuthDTO.getRelationship());
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

	public void sendPasswordResetLink(String emailOrPhone) {
		boolean isEmail = emailOrPhone.contains("@");
		if (isEmail) {
			sendPasswordResetLinkByEmail(emailOrPhone);
		} else {
			sendPasswordResetLinkByPhone(emailOrPhone);
		}
	}

	public void resetPassword(PasswordResetDTO resetDTO) {
		String token = resetDTO.getToken();
		String newPassword = resetDTO.getNewPassword();
		Patient patient = tokenService.validateAndGetPatientByPasswordResetToken(token);
		patient.setPassword(passwordEncoder.encode(newPassword));
		patientRepository.save(patient);
	}

	private void sendPasswordResetLinkByEmail(String email) {
		Patient patient = patientRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
		String token = tokenService.generatePasswordResetToken(patient);
		String resetLink = passwordResetRequestUrl + token;
		emailService.sendEmail(email, "Password reset", resetLink);
	}

	private void sendPasswordResetLinkByPhone(String phoneNumber) {
		Patient patient = patientRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
		String token = tokenService.generatePasswordResetToken(patient);
		String resetLink = passwordResetRequestUrl + token;
		smsService.sendPasswordResetSms(phoneNumber, resetLink);
	}
}
