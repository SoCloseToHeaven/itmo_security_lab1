package ru.ifmo.lab.controller.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private final String username;
    private final String password;
    private final String nickname;
    private final String email;
}

