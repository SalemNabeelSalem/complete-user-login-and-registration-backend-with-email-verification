package com.salemnabeel.demo.services;

import com.salemnabeel.demo.entities.User;
import com.salemnabeel.demo.repositories.UserRepository;
import com.salemnabeel.demo.entities.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
        );
    }

    public String signUpUser(User userRequest) {

        boolean userExists = userRepository.findByEmail(userRequest.getEmail()).isPresent();

        if (userExists) {

            // TODO: check of attributes are the same.

            // TODO: if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());

        userRequest.setPassword(encodedPassword);

        userRepository.save(userRequest);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
            token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), userRequest
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: send email.

        return token;
    }

    public int enableUser(String email) {

        return userRepository.enableUser(email);
    }
}