package com.sparta.hanghaemini.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.hanghaemini.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

// 게시글 상세 조회
@NoArgsConstructor
@Getter
public class PostCommentDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private TeamDto team;
    private CategoryDto category;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private List<CommentResponseDto> comment;

    public PostCommentDto(Post post, TeamDto team, CategoryDto category, List<CommentResponseDto> comment) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getUser().getNickname();
        this.team = team;
        this.createdAt = post.getCreatedAt();
        this.category = category;
        this.comment = comment;
    }
}
