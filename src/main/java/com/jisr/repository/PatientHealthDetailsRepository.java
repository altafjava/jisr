package com.jisr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.Patient;
import com.jisr.entity.PatientHealthDetails;

public interface PatientHealthDetailsRepository extends JpaRepository<PatientHealthDetails, Long> {

	Optional<PatientHealthDetails> findByPatient(Patient patient);

}
