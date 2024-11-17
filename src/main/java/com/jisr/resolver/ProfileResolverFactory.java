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
public class ProfileResolverFactory {

	private final Map<RoleEnum, ProfileResolver> resolvers;

	@Autowired
	public ProfileResolverFactory(List<ProfileResolver> resolverList) {
		Map<RoleEnum, ProfileResolver> resolverMap = new HashMap<>();
		for (ProfileResolver resolver : resolverList) {
			if (resolver instanceof PatientProfileResolver) {
				resolverMap.put(RoleEnum.PATIENT, resolver);
				resolverMap.put(RoleEnum.CAREGIVER, resolver);
			} else if (resolver instanceof HealthcareProviderResolver) {
				resolverMap.put(RoleEnum.HEALTHCARE_PROVIDER, resolver);
			} else if (resolver instanceof AdminProfileResolver) {
				resolverMap.put(RoleEnum.ADMIN, resolver);
			} else {
				throw new IllegalArgumentException("Unsupported resolver: " + resolver.getClass());
			}
		}
		this.resolvers = Collections.unmodifiableMap(resolverMap);
	}

	public ProfileResolver getResolver(RoleEnum RoleEnum) {
		return Optional.ofNullable(resolvers.get(RoleEnum)).orElseThrow(() -> new IllegalArgumentException("No resolver found for user type: " + RoleEnum));
	}
}
