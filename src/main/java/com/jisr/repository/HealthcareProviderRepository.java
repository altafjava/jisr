package com.jisr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.HealthcareProvider;

public interface HealthcareProviderRepository extends JpaRepository<HealthcareProvider, Long> {
	Optional<HealthcareProvider> findByEmail(String email);
}
