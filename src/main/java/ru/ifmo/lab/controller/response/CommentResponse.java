package ru.ifmo.lab.controller.response;

import lombok.Data;

@Data
public class CommentResponse {
    private final Long id;
    private final String content;
    private final String profile;
}
