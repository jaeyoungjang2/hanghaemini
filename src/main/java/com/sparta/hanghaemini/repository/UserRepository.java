package com.sparta.hanghaemini.repository;

import com.sparta.hanghaemini.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByNickName(String nickname);
}
