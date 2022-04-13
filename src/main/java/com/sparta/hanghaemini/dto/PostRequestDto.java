package com.sparta.hanghaemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class PostRequestDto{

    private String title;
    private String content;
    private TeamDto team;
    private LocalDateTime createdAt;
    private CategoryDto category;
}