package com.sparta.hanghaemini.service;

import com.sparta.hanghaemini.dto.CommentRequestDto;
import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.model.Comment;
import com.sparta.hanghaemini.model.Post;
import com.sparta.hanghaemini.model.User;
import com.sparta.hanghaemini.repository.CommentRepository;
import com.sparta.hanghaemini.repository.PostRepository;
import com.sparta.hanghaemini.repository.UserRepository;
import com.sparta.hanghaemini.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    // 댓글 작성
    @Transactional
    public JudgeSuccessDto addComment(CommentRequestDto commentDto, UserDetailsImpl userDetails) {
        Long postId = commentDto.getPostId();
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (commentDto.getContent() == null || commentDto.getContent().trim().isEmpty()) {
            boolean ok = false;
            String message = "댓글 저장 실패";

            return new JudgeSuccessDto(ok, message);
        }


        User user = userDetails.getUser();
        // save 없이 이것만으로 저장됨
        new Comment(commentDto.getContent(), post, user);
//        commentRepository.save(comment);

        boolean ok = true;
        String message = "댓글 저장 완료";

       return new JudgeSuccessDto(ok, message);
    }

    // 댓글 수정
    @Transactional
    public JudgeSuccessDto updateComment(Long commentId, CommentRequestDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다."));
        if (commentId == null) {
            boolean ok = false;
            String message = "댓글 수정 실패";

            return new JudgeSuccessDto(ok, message);
        }
        comment.update(commentDto);
        boolean ok = true;
        String message = "댓글 수정 완료";

        return new JudgeSuccessDto(ok, message);
    }

    // 댓글 삭제 - 실패할 수가 있나?
    public JudgeSuccessDto deleteComment(Long commentId) {
        if (commentId == null) {
            boolean ok = false;
            String message = "댓글 삭제 실패";

            return new JudgeSuccessDto(ok, message);
        }

        commentRepository.deleteById(commentId);

        boolean ok = true;
        String message = "댓글 삭제 완료";

        return new JudgeSuccessDto(ok, message);
    }
}