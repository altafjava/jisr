package com.jisr.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserProfile2 {
	private Profile2 profile;
	private String userType;
	private int completionPercentage;
	private int totalWeight;
	private int completedWeight;
	private int remainingWeight;
	private List<CompletedField> completedFields;
	private List<RemainingField> remainingFields;
}
