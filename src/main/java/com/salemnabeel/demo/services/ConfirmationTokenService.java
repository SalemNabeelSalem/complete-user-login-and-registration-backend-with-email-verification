package com.salemnabeel.demo.services;

import com.salemnabeel.demo.entities.ConfirmationToken;
import com.salemnabeel.demo.repositories.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {

        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> findToken(String token) {

        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {

        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}