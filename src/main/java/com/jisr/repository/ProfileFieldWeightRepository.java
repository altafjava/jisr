package com.jisr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jisr.entity.ProfileFieldWeight;

public interface ProfileFieldWeightRepository extends JpaRepository<ProfileFieldWeight, Long> {

	@Query("SELECT pfw FROM ProfileFieldWeight pfw WHERE pfw.entityName IN :entityNames")
	List<ProfileFieldWeight> findByEntityNames(@Param("entityNames") List<String> entityNames);
}
