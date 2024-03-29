package com.agregating.events.service.implementation;

import com.agregating.events.domain.Comment;
import com.agregating.events.repository.CommentRepository;
import com.agregating.events.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * implementation of Comment Service
 *
 * @author Dmitri Tšornõi
 */
@Service
public class CommentServiceImplementation implements CommentService {

  private final CommentRepository repository;

  @Autowired
  public CommentServiceImplementation(CommentRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public List<Comment> findAllComments() {
    return repository.findAll();
  }

  @Override
  @Transactional
  public Comment saveComment(Comment comment) {
    return repository.save(comment);
  }

  @Override
  @Transactional
  public boolean deleteComment(UUID id) {
    Optional<Comment> optionalComment = repository.findById(id);
    if (optionalComment.isPresent()) {
      repository.delete(optionalComment.get());
      return true;
    }

    return false;
  }
}
