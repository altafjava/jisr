package com.jisr.constant;

import java.util.List;
import com.jisr.entity.HealthcareProvider;
import com.jisr.entity.PatientProfile;
import com.jisr.entity.RoleEnum;
import com.jisr.entity.User;

public enum UserTypeEntityMapping {
	ADMIN(RoleEnum.ADMIN.name(), List.of(User.class.getSimpleName())),
	PATIENT(RoleEnum.PATIENT.name(), List.of(User.class.getSimpleName(), PatientProfile.class.getSimpleName())),
	CAREGIVER(RoleEnum.CAREGIVER.name(), List.of(User.class.getSimpleName(), PatientProfile.class.getSimpleName())),
	HEALTHCARE_PROVIDER(RoleEnum.HEALTHCARE_PROVIDER.name(), List.of(User.class.getSimpleName(), HealthcareProvider.class.getSimpleName()));

	private final String userType;
	private final List<String> entityNames;

	UserTypeEntityMapping(String userType, List<String> entityNames) {
		this.userType = userType;
		this.entityNames = entityNames;
	}

	public String getUserType() {
		return userType;
	}

	public List<String> getEntityNames() {
		return entityNames;
	}

	public static List<String> getEntitiesForUserType(String userType) {
		for (UserTypeEntityMapping mapping : values()) {
			if (mapping.getUserType().equalsIgnoreCase(userType)) {
				return mapping.getEntityNames();
			}
		}
		throw new IllegalArgumentException("Invalid user type: " + userType);
	}
}
