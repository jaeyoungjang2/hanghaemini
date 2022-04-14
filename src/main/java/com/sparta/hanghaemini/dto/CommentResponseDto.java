package com.sparta.hanghaemini.dto;

import com.sparta.hanghaemini.model.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private boolean is_writer;
    private String nickname;
    private String content;

    public CommentResponseDto(Comment comment, boolean equals) {
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.is_writer = equals;
    }
}
