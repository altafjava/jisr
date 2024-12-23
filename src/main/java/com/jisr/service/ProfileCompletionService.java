package com.jisr.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.jisr.entity.ProfileFieldWeight;
import com.jisr.entity.RoleEnum;
import com.jisr.entity.User;
import com.jisr.model.CompletedField;
import com.jisr.model.ProgressProfile;
import com.jisr.model.RemainingField;
import com.jisr.repository.ProfileFieldWeightRepository;
import com.jisr.util.CaseUtils;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileCompletionService {

	private final ProfileFieldWeightRepository profileFieldWeightRepository;

	public ProgressProfile calculateProfileCompletion(User user, Optional<?> profileOptional) {
		RoleEnum roleEnum = user.getUserType();
		List<String> entityNames = roleEnum.getEntityNames();
		Map<String, Object> fieldValues = null;
		if (profileOptional.isEmpty()) {
			fieldValues = extractFieldValues(user);
		} else {
			fieldValues = extractFieldValues(profileOptional.get());
		}
		List<ProfileFieldWeight> fieldWeights = profileFieldWeightRepository.findByEntityNames(entityNames);
		return calculateCompletionPercentage(fieldValues, fieldWeights, roleEnum.name());
	}

	private Map<String, Object> extractFieldValues(Object entity) {
		Map<String, Object> fieldValues = new HashMap<>();
		extractFieldsRecursively(entity, fieldValues);
		return fieldValues;
	}

	private void extractFieldsRecursively(Object entity, Map<String, Object> fieldValues) {
		if (entity == null)
			return;
		Class<?> clazz = entity.getClass();
		String entityName = clazz.getSimpleName();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(entity);
				String fieldName = entityName + "." + field.getName();
				fieldValues.put(fieldName, value);
				if (field.isAnnotationPresent(OneToOne.class) || field.isAnnotationPresent(ManyToOne.class)) {
					extractFieldsRecursively(value, fieldValues);
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Error accessing field: " + field.getName(), e);
			}
		}
	}

	private ProgressProfile calculateCompletionPercentage(Map<String, Object> fieldValues, List<ProfileFieldWeight> profileFieldWeights, String userType) {
		int totalWeight = 0;
		int completedWeight = 0;
		int remainingWeight = 0;
		List<CompletedField> completedFields = new ArrayList<>();
		List<RemainingField> remainingFields = new ArrayList<>();
		for (ProfileFieldWeight pfw : profileFieldWeights) {
			totalWeight += pfw.getWeight();
			String camelCaseFieldName = CaseUtils.toCamelCase(pfw.getFieldName());
			Object value = fieldValues.get(pfw.getEntityName() + "." + camelCaseFieldName);
			if (value != null && isFieldComplete(value)) {
				completedWeight += pfw.getWeight();
				completedFields.add(new CompletedField(pfw.getEntityName(), pfw.getFieldName(), camelCaseFieldName, value, pfw.getWeight()));
			} else {
				remainingWeight += pfw.getWeight();
				remainingFields.add(new RemainingField(pfw.getEntityName(), pfw.getFieldName(), camelCaseFieldName, pfw.getWeight()));
			}
		}
		int completionPercentage = totalWeight == 0 ? 0 : (completedWeight * 100) / totalWeight;
		ProgressProfile profileCompletion = ProgressProfile.builder().userType(userType).completionPercentage(completionPercentage).totalWeight(totalWeight)
				.completedWeight(completedWeight).remainingWeight(remainingWeight).completedFields(completedFields).remainingFields(remainingFields).build();
		return profileCompletion;
	}

	private boolean isFieldComplete(Object value) {
		if (value instanceof String) {
			return !((String) value).isEmpty();
		} else if (value instanceof Collection<?>) {
			return !((Collection<?>) value).isEmpty();
		}
		return value != null; // Default: non-null means complete
	}
}
