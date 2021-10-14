package com.salemnabeel.demo.controllers;

import com.salemnabeel.demo.models.RegistrationRequest;
import com.salemnabeel.demo.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/public/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("")
    public String register(@RequestBody RegistrationRequest registerRequest) {

        return registrationService.register(registerRequest);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationService.confirmToken(token);
    }
}