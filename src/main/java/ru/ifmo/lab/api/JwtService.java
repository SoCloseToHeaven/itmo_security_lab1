package ru.ifmo.lab.api;

public interface JwtService {

    String generateToken(String username);

    String extractUsername(String token);
}
