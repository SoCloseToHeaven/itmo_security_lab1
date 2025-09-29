package ru.ifmo.lab.controller.request;

import lombok.Data;

@Data
public class LoginRequest {

    private final String username;

    private final String password;
}
