package com.jisr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jisr.entity.PatientProfile;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {

//	Optional<PatientProfile> findByUserId(Long userId);
	
	@Query("SELECT p FROM PatientProfile p WHERE p.user.id = :userId")
    Optional<PatientProfile> findByUserId(@Param("userId") Long userId);

}
