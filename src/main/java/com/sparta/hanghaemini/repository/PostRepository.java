package com.sparta.hanghaemini.repository;


import com.sparta.hanghaemini.model.Post;
import com.sparta.hanghaemini.model.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryName(String categoryName);

    Optional<Post> findByTeam(Team foundteam);
}
