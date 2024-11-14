package com.jisr.service;

import com.jisr.dto.PasswordResetDTO;
import com.jisr.dto.PatientAuthDTO;
import com.jisr.dto.PatientHealthDetailsDTO;
import com.jisr.entity.Patient;

public interface PatientAuthService {

	Patient registerUser(PatientAuthDTO userDTO);

	void savePatientHealthDetails(Long userId, PatientHealthDetailsDTO patientInitialInfoDTO);

	void sendPasswordResetLink(String emailOrPhone);

	void resetPassword(PasswordResetDTO resetDTO);

}
