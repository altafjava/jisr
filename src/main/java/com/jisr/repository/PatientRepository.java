package com.jisr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByEmail(String email);

	Patient findByPhoneNumber(String phoneNumber);

}
