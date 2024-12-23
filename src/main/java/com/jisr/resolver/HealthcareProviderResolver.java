package com.jisr.resolver;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jisr.repository.HealthcareProviderRepository;

@Component
public class HealthcareProviderResolver implements ProfileResolver {

	@Autowired
	private HealthcareProviderRepository healthcareProviderRepository;

	@Override
	public Optional<Object> resolveProfile(Long userId) {
		return healthcareProviderRepository.findByUserId(userId).map(profile -> (Object) profile);
	}
}
