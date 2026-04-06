package com.example.springboot_twitter.comment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostIdOrderByIdDesc(Long postId);

    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);

    @Query("SELECT c FROM Comment c JOIN FETCH c.post")
    List<Comment> findAll();

    @EntityGraph(attributePaths = "post", type= EntityGraph.EntityGraphType.LOAD)
    List<Comment> findAllWithPostBy();
}
