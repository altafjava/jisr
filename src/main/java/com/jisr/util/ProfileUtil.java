package com.jisr.util;

import java.time.LocalDate;
import java.util.Optional;
import com.jisr.entity.PatientProfile;
import com.jisr.entity.User;
import com.jisr.model.Profile2;

public class ProfileUtil {

	
//	public static Profile buildProfile(User user, Optional<Object> patientProfileOptional) {
//		Profile profile = new Profile();
//		profile.setUsername(user.getUsername());
//		profile.setFirstName(user.getFirstName());
//		profile.setFatherName(user.getFatherName());
//		profile.setLastName(user.getLastName());
//		profile.setFullName(user.getFullName());
//		profile.setEmail(user.getEmail());
//		profile.setPhoneNumber(user.getPhoneNumber());
//		profile.setRoles(user.getRoles());
//		if (patientProfileOptional.isPresent()) {
//			PatientProfile patientProfile = patientProfileOptional.get();
//			profile.setRelationship(patientProfile.getRelationship());
//			profile.setCancerType(patientProfile.getCancerType());
//			profile.setTreatmentType(patientProfile.getTreatmentType());
//			profile.setMedicinesAndDoses(patientProfile.getMedicines());
//			profile.setChemotherapyHistory(patientProfile.getChemoHistory());
//			profile.setHasCentralCatheter(patientProfile.getCentralCatheterInfo());
//			profile.setHeight(patientProfile.getHeight());
//			profile.setWeight(patientProfile.getWeight());
//			profile.setBmi(patientProfile.getBmi());
//			profile.setAge(AgeCalculator.calculate(patientProfile.getDateOfBirth(), LocalDate.now()).getYmd().getYears());
//			profile.setDateOfBirth(patientProfile.getDateOfBirth());
//			profile.setGender(patientProfile.getGender());
//			profile.setHealthcareRegion(patientProfile.getRegion());
//		}
//		return profile;
//	}
	
	public static Profile2 buildProfile1(User user, Optional<PatientProfile> patientProfileOptional) {
		Profile2 profile = new Profile2();
		profile.setUsername(user.getUsername());
		profile.setFirstName(user.getFirstName());
		profile.setFatherName(user.getFatherName());
		profile.setLastName(user.getLastName());
		profile.setFullName(user.getFullName());
		profile.setEmail(user.getEmail());
		profile.setPhoneNumber(user.getPhoneNumber());
		profile.setRoles(user.getRoles());
		if (patientProfileOptional.isPresent()) {
			PatientProfile patientProfile = patientProfileOptional.get();
			profile.setRelationship(patientProfile.getRelationship());
			profile.setCancerType(patientProfile.getCancerType());
			profile.setTreatmentType(patientProfile.getTreatmentType());
//			profile.setMedicinesAndDoses(patientProfile.getMedicines());
//			profile.setChemotherapyHistory(patientProfile.getChemoHistory());
//			profile.setHasCentralCatheter(patientProfile.getCentralCatheterInfo());
//			profile.setHeight(patientProfile.getHeight());
//			profile.setWeight(patientProfile.getWeight());
//			profile.setBmi(patientProfile.getBmi());
			profile.setAge(Calculator.calculateAge(patientProfile.getDateOfBirth(), LocalDate.now()).getYmd().getYears());
			profile.setDateOfBirth(patientProfile.getDateOfBirth());
//			profile.setGender(patientProfile.getGender());
//			profile.setHealthcareRegion(patientProfile.getRegion());
		}
		return profile;
	}
	
	

}
