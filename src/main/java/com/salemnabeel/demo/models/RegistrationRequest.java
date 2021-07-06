package com.salemnabeel.demo.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RegistrationRequest {

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String password;
}