package com.jisr.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.WaitingQueue;

public interface WaitingQueueRepository extends JpaRepository<WaitingQueue, Long> {

	long countByCreatedAtBefore(LocalDateTime timestamp);

	Optional<WaitingQueue> findByUsername(String username);
}