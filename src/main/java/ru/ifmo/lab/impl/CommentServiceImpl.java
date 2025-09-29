package ru.ifmo.lab.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifmo.lab.api.CommentService;
import ru.ifmo.lab.controller.response.CommentResponse;
import ru.ifmo.lab.impl.data.Comment;
import ru.ifmo.lab.impl.data.Profile;

import java.util.List;

@Service
@AllArgsConstructor
class CommentServiceImpl implements CommentService {
    private final ProfileRepository profileRepository;
    private final CommentRepository commentRepository;
    private final JwtServiceImpl jwtServiceImpl;


    public List<CommentResponse> getComment(String username) {
        Profile profile = profileRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return commentRepository.findByProfile(profile)
                .stream()
                .map(p -> new CommentResponse(p.getId(), p.getContent(), p.getProfile().getNickname()))
                .toList();
    }

    public Comment createComment(String token, String content) {
        String username = jwtServiceImpl.extractUsername(token);
        Profile profile = profileRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        Comment comment = Comment.builder().content(content).profile(profile).build();
        return commentRepository.save(comment);
    }
}
