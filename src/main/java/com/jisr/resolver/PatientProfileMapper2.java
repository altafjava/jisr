//package com.jisr.resolver;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import org.springframework.stereotype.Component;
//import com.jisr.entity.PatientProfile;
//import com.jisr.entity.User;
//import com.jisr.model.Profile2;
//import com.jisr.util.AgeCalculator;
//
//@Component
//public class PatientProfileMapper2 implements ProfileMapper {
//
//	@Override
//	public Profile2 mapToProfile(User user, Optional<Object> profileOptional) {
//		Profile2 profile = new Profile2();
//		profile.setUsername(user.getUsername());
//		profile.setFirstName(user.getFirstName());
//		profile.setFatherName(user.getFatherName());
//		profile.setLastName(user.getLastName());
//		profile.setFullName(user.getFullName());
//		profile.setEmail(user.getEmail());
//		profile.setPhoneNumber(user.getPhoneNumber());
//		profile.setRoles(user.getRoles());
//		if (profileOptional.isPresent()) {
//			PatientProfile patientProfile = (PatientProfile) profileOptional.get();
//			profile.setRelationship(patientProfile.getRelationship());
//			profile.setCancerType(patientProfile.getCancerType());
//			profile.setTreatmentType(patientProfile.getTreatmentType());
//			profile.setMedicinesAndDoses(patientProfile.getMedicines());
//			profile.setChemotherapyHistory(patientProfile.getChemoHistory());
//			profile.setHasCentralCatheter(patientProfile.getCentralCatheterInfo());
//			profile.setHeight(patientProfile.getHeight());
//			profile.setWeight(patientProfile.getWeight());
//			profile.setBmi(patientProfile.getBmi());
//			profile.setDateOfBirth(patientProfile.getDateOfBirth());
//			profile.setGender(patientProfile.getGender());
//			profile.setHealthcareRegion(patientProfile.getRegion());
//			profile.setAge(AgeCalculator.calculate(patientProfile.getDateOfBirth(), LocalDate.now()).getYmd().getYears());
//		}
//		return profile;
//	}
//}
