package com.sparta.hanghaemini.dto;

import com.sparta.hanghaemini.model.Category;
import com.sparta.hanghaemini.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CategoryDto {

    private String name;

    public CategoryDto(Category category) {
        this.name = category.getName();
    }

    public CategoryDto(String name) {
        this.name = name;
    }
}