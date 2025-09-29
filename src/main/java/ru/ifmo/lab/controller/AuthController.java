package ru.ifmo.lab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.lab.api.AuthService;
import ru.ifmo.lab.controller.request.LoginRequest;
import ru.ifmo.lab.controller.request.RegisterRequest;
import ru.ifmo.lab.controller.response.JwtReponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getNickname(),
                registerRequest.getEmail()
        );
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtReponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                new JwtReponse(
                        authService.login(loginRequest.getUsername(), loginRequest.getPassword()))
        );
    }

}
