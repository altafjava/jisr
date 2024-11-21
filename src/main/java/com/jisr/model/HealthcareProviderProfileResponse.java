package com.jisr.model;

import com.jisr.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HealthcareProviderProfileResponse {
	private UserDTO userProfile;
	private HealthcareProviderData providerProfile;
	private ProgressProfile progressProfile;
}
