package com.jisr.resolver;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.jisr.entity.HealthcareProvider;
import com.jisr.entity.User;
import com.jisr.model.Profile2;

@Component
public class HealthcareProviderProfileMapper implements ProfileMapper {

	@Override
	public Profile2 mapToProfile(User user, Optional<Object> profileOptional) {
		Profile2 profile = new Profile2();
		profile.setUsername(user.getUsername());
		profile.setFirstName(user.getFirstName());
		profile.setFatherName(user.getFatherName());
		profile.setLastName(user.getLastName());
		profile.setFullName(user.getFullName());
		profile.setEmail(user.getEmail());
		profile.setPhoneNumber(user.getPhoneNumber());
		profile.setRoles(user.getRoles());
		if (profileOptional.isPresent()) {
			HealthcareProvider healthcareProvider = (HealthcareProvider) profileOptional.get();
			profile.setSpecialization(healthcareProvider.getSpecialization());
			profile.setQualification(healthcareProvider.getQualification());
			profile.setExperience(healthcareProvider.getExperience());
//			profile.setGender(healthcareProvider.getGender());
			profile.setDateOfBirth(healthcareProvider.getDateOfBirth());
		}
		return profile;
	}
}
