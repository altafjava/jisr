package com.jisr.model;

import java.util.List;
import java.util.Set;
import com.jisr.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfile extends ProgressProfile {
	private String username;
	private String firstName;
	private String fatherName;
	private String lastName;
	private String fullName;
	private String email;
	private String phoneNumber;
	private Set<Role> roles;

	UserProfile(String userType, int completionPercentage, int totalWeight, int completedWeight, int remainingWeight, List<CompletedField> completedFields,
			List<RemainingField> remainingFields) {
		super(userType, completionPercentage, totalWeight, completedWeight, remainingWeight, completedFields, remainingFields);
		// TODO Auto-generated constructor stub
	}
}
