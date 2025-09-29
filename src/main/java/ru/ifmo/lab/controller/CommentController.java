package ru.ifmo.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.lab.api.CommentService;
import ru.ifmo.lab.controller.request.CreateCommentRequest;
import ru.ifmo.lab.controller.response.CommentResponse;
import ru.ifmo.lab.impl.data.Comment;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/data")
    public ResponseEntity<List<CommentResponse>> getPosts(@RequestParam String username) {
        return ResponseEntity.ok(commentService.getComment(username));
    }

    @PostMapping("/notes")
    public ResponseEntity<?> createPost(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody CreateCommentRequest request
    ) {
        String token = authHeader.replace("Bearer ", "");
        Comment comment = commentService.createComment(token, request.getContent());
        return ResponseEntity.ok("Post created with id: " + comment.getId()); // API?
    }


}
