package ru.ifmo.lab.api;

import ru.ifmo.lab.controller.response.CommentResponse;
import ru.ifmo.lab.impl.data.Comment;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getComment(String username);

    Comment createComment(String token, String content);
}
