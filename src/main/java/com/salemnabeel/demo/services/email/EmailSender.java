package com.salemnabeel.demo.services.email;

public interface EmailSender {

    void send(String to, String email);

    String buildEmail(String name, String link);
}