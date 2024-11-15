package com.jisr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.SystemSetting;

public interface GlobalSettingRepository extends JpaRepository<SystemSetting, Long> {
	Optional<SystemSetting> findByName(String name);
}
