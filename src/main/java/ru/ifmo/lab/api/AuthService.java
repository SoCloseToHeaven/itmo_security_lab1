package ru.ifmo.lab.api;

public interface AuthService {

    void register(String username, String password, String nickname, String email);

    String login(String username, String password);
}
