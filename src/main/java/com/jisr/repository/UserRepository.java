package com.jisr.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jisr.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.phoneNumber = :phoneNumber")
	Optional<User> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Query("SELECT COUNT(u) FROM User u WHERE u.isActive = false AND u.createdDate < :createdDate")
	long countInactiveUsersBefore(@Param("createdDate") LocalDateTime createdDate);

	@Query("SELECT u FROM User u JOIN u.roles r WHERE u.isActive = false AND r.name = :roleName")
	List<User> findInactiveUsersByRole(@Param("roleName") String roleName);

	@Query("SELECT u FROM User u JOIN u.roles r WHERE u.isActive = false AND r.name IN :roleNames")
	List<User> findInactiveUsersByRoles(@Param("roleNames") List<String> roleNames);

}
