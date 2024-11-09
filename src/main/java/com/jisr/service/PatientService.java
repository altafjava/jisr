package com.jisr.service;

import com.jisr.dto.PatientHealthDetailsDTO;
import com.jisr.dto.PatientDTO;
import com.jisr.entity.Patient;

public interface PatientService {

	Patient registerUser(PatientDTO userDTO);

	void savePatientHealthDetails(Long userId, PatientHealthDetailsDTO patientInitialInfoDTO);

}
