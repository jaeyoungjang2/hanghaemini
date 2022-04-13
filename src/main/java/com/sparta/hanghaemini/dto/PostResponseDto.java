package com.sparta.hanghaemini.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.hanghaemini.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

// 게시글 조회
@NoArgsConstructor
@Getter
public class PostResponseDto{
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private TeamDto team;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private CategoryDto category;

    public PostResponseDto(Post post, TeamDto team, CategoryDto category) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getUser().getNickname();
        this.team = team;
        this.createdAt = post.getCreatedAt();
        this.category = category;
    }
}



