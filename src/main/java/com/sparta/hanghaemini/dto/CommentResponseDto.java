package com.sparta.hanghaemini.dto;

import com.sparta.hanghaemini.model.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private String nickname;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
    }
}
