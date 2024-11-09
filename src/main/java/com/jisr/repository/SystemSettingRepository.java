package com.jisr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jisr.entity.SystemSetting;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE SystemSetting s SET s.value = :value WHERE s.key = :key")
	void updateValueByKey(@Param("key") String key, @Param("value") String value);

	@Query("SELECT s.value FROM SystemSetting s WHERE s.key = :key")
    String findValueByKey(@Param("key") String key);
}
