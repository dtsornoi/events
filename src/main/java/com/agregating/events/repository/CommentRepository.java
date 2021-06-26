package com.agregating.events.repository;

import com.agregating.events.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JpaRepository implementation for Comment POJO
 *
 * @author Dmitri Tšornõi
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, UUID> {}
