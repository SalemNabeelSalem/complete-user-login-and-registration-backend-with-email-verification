package com.salemnabeel.demo.repositories;

import com.salemnabeel.demo.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE confirmation_tokens ct SET ct.confirmed_at = ?2 WHERE ct.token = ?1",
        nativeQuery = true
    )
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}