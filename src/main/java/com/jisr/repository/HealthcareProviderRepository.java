package com.jisr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jisr.entity.HealthcareProvider;

public interface HealthcareProviderRepository extends JpaRepository<HealthcareProvider, Long> {

	@Query("SELECT p FROM HealthcareProvider p WHERE p.user.id = :userId")
	Optional<HealthcareProvider> findByUserId(@Param("userId") Long userId);
}
