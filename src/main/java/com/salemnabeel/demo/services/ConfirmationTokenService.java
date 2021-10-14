package com.salemnabeel.demo.services;

import com.salemnabeel.demo.entities.ConfirmationToken;
import com.salemnabeel.demo.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {

        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> findToken(String token) {

        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {

        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}