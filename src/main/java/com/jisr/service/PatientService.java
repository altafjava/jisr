package com.jisr.service;

import org.springframework.web.multipart.MultipartFile;
import com.jisr.dto.PatientProfileDTO;

public interface PatientService {

	PatientProfileDTO getPatientProfile(Long patientId);

	void updatePatientProfile(Long patientId, PatientProfileDTO patientProfileDTO);

	void uploadPatientFile(Long patientId, MultipartFile file);

}
