package com.agregating.events.service;

import com.agregating.events.domain.Comment;
import com.agregating.events.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{

    private CommentRepository repository;

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
    public boolean deleteComment(long id) {
        Optional<Comment> optionalComment = repository.findById(id);
        if (optionalComment.isPresent()){
            repository.delete(optionalComment.get());
            return true;
        }

        return false;
    }
}
