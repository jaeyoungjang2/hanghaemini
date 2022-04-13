package com.sparta.hanghaemini.repository;

import com.sparta.hanghaemini.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
