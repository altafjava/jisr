package com.jisr.resolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jisr.entity.RoleEnum;

@Component
public class ProfileMapperFactory {

	private final Map<RoleEnum, ProfileMapper> profileMappers;

	@Autowired
	public ProfileMapperFactory(List<ProfileMapper> profileMapperList) {
		Map<RoleEnum, ProfileMapper> mapperMap = new HashMap<>();
		for (ProfileMapper mapper : profileMapperList) {
			if (mapper instanceof PatientProfileMapper) {
				mapperMap.put(RoleEnum.PATIENT, mapper);
				mapperMap.put(RoleEnum.CAREGIVER, mapper);
			} else if (mapper instanceof HealthcareProviderProfileMapper) {
				mapperMap.put(RoleEnum.HEALTHCARE_PROVIDER, mapper);
			} else if (mapper instanceof AdminProfileMapper) {
				mapperMap.put(RoleEnum.ADMIN, mapper);
			} else {
				throw new IllegalArgumentException("Unsupported mapper: " + mapper.getClass());
			}
		}
		this.profileMappers = Collections.unmodifiableMap(mapperMap);
	}

	public ProfileMapper getMapper(RoleEnum roleEnum) {
		return Optional.ofNullable(profileMappers.get(roleEnum)).orElseThrow(() -> new IllegalArgumentException("No mapper found for user type: " + roleEnum));
	}
}
