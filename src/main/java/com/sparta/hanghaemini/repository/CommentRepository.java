package com.sparta.hanghaemini.repository;

import com.sparta.hanghaemini.model.Comment;
import com.sparta.hanghaemini.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
}
