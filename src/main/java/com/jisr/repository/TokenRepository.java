package com.jisr.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jisr.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    // Optional: Find all expired tokens for cleanup (if needed)
    List<Token> findAllByExpiresAtBefore(LocalDateTime localDateTime);
}
