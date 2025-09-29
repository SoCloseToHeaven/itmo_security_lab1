package ru.ifmo.lab.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ifmo.lab.api.AuthService;
import ru.ifmo.lab.api.JwtService;
import ru.ifmo.lab.impl.data.Profile;
import ru.ifmo.lab.impl.data.User;

@Service
@AllArgsConstructor
class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(String username, String password, String nickname, String email) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder().username(username).password(passwordEncoder.encode(password)).build();
        userRepository.save(user);

        Profile profile = Profile.builder().nickname(nickname).email(email).user(user).build();
        profileRepository.save(profile);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username doesn't exist"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtService.generateToken(user.getUsername());
    }
}
