package com.agregating.events.service;

import com.agregating.events.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAllComments();

    Comment saveComment(Comment comment);

    boolean deleteComment(long id);
}
