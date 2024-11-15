package com.jisr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.HealthcareProviderOld;

public interface HealthcareProviderRepository extends JpaRepository<HealthcareProviderOld, Long> {
	Optional<HealthcareProviderOld> findByEmail(String email);
}
