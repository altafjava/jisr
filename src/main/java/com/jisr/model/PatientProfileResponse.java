package com.jisr.model;

import com.jisr.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientProfileResponse {
	private UserDTO userProfile;
	private PatientProfileData patientProfile;
	private ProgressProfile progressProfile;
}
