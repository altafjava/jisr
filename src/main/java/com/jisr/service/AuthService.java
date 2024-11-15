package com.jisr.service;

import com.jisr.dto.PasswordResetDTO;
import com.jisr.dto.RegistrationResponse;
import com.jisr.dto.SignupDTO;

public interface AuthService {

	RegistrationResponse registerUser(SignupDTO signupDTO);

//	void savePatientHealthDetails(Long userId, PatientHealthDetailsDTO patientInitialInfoDTO);
	void sendPasswordResetLink(String emailOrPhone);

	void resetPassword(PasswordResetDTO resetDTO);

	String refreshAccessToken(String refreshToken);

}
