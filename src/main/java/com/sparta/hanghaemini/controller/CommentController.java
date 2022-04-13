package com.sparta.hanghaemini.controller;

import com.sparta.hanghaemini.dto.CommentRequestDto;
import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.security.UserDetailsImpl;
import com.sparta.hanghaemini.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/api/comments")
    public JudgeSuccessDto addComment(@RequestBody CommentRequestDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.addComment(commentDto, userDetails);
    }

    // 댓글 수정
    @PutMapping("/api/comments/{commentId}")
    public JudgeSuccessDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentDto) {
        commentService.updateComment(commentId, commentDto);
        return commentService.updateComment(commentId, commentDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    public JudgeSuccessDto deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
