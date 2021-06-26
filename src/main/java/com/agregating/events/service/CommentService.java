package com.agregating.events.service;

import com.agregating.events.domain.Comment;

import java.util.List;
import java.util.UUID;

/**
 * interface for Comment Service
 *
 * @author Dmitri Tšornõi
 */
public interface CommentService {
  List<Comment> findAllComments();

  Comment saveComment(Comment comment);

  boolean deleteComment(UUID id);
}
