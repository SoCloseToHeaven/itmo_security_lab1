package ru.ifmo.lab.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.lab.impl.data.Comment;
import ru.ifmo.lab.impl.data.Profile;

import java.util.List;

@Repository
interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProfile(Profile profile);
}
