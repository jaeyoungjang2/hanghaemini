package com.sparta.hanghaemini.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.sparta.hanghaemini.dto.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    // 카테고리 이름
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category(CategoryDto categoryDto) {
        this.name = categoryDto.getName();
    }

    public void changeCat(String name) {
        this.name = name;
    }
}