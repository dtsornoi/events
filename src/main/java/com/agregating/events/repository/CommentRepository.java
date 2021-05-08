package com.agregating.events.repository;

import com.agregating.events.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository implementation for Comment POJO
 *
 * @author Dmitri Tšornõi
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
