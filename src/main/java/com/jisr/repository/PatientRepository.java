package com.jisr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Optional<Patient> findByEmail(String email);

	Optional<Patient> findByPhoneNumber(String phoneNumber);

}
