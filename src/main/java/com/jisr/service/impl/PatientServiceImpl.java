package com.jisr.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.jisr.dto.PatientProfileDTO;
import com.jisr.entity.Patient;
import com.jisr.entity.PatientHealthDetails;
import com.jisr.repository.PatientRepository;
import com.jisr.service.FileStorageService;
import com.jisr.service.PatientService;
import com.jisr.util.PatientServiceUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final FileStorageService fileStorageService;

	@Override
	@PreAuthorize("#patientId == principal.id")
	public PatientProfileDTO getPatientProfile(Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
		PatientHealthDetails patientHealthDetails = patient.getPatientHealthDetails();
		PatientProfileDTO patientProfileDTO = PatientServiceUtil.buildPatientProfileDTO(patient, patientHealthDetails);
		return patientProfileDTO;
	}

	@Override
	@Transactional
	@PreAuthorize("#patientId == principal.id")
	public void updatePatientProfile(Long patientId, PatientProfileDTO patientProfileDTO) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
		PatientServiceUtil.updatePatient(patientProfileDTO, patient);
		PatientHealthDetails patientHealthDetails = PatientServiceUtil.updatePatientHealthDetails(patientProfileDTO, patient);
		PatientServiceUtil.updateHeightWeightAndBMI(patientProfileDTO, patientHealthDetails);
		PatientServiceUtil.updateDateOfBirthAndAge(patientProfileDTO, patientHealthDetails);
		patient.setPatientHealthDetails(patientHealthDetails);
		patientRepository.save(patient);
	}

	@Override
	public void uploadPatientFile(Long patientId, MultipartFile file) {
		if (!file.getContentType().matches("application/pdf|image/.*")) {
			throw new IllegalArgumentException("Only PDF or image files are allowed.");
		}
		fileStorageService.storeFile(patientId, file);
	}
}
