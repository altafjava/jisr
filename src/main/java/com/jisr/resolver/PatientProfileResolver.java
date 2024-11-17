package com.jisr.resolver;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jisr.repository.PatientProfileRepository;

@Component
public class PatientProfileResolver implements ProfileResolver {

	@Autowired
	private PatientProfileRepository patientProfileRepository;

	@Override
	public Optional<Object> resolveProfile(Long userId) {
        return patientProfileRepository.findByUserId(userId).map(profile -> (Object) profile);
	}
}
