
package com.sparta.hanghaemini.controller;

import com.sparta.hanghaemini.dto.JudgeSuccessDto;
import com.sparta.hanghaemini.dto.PostCommentDto;
import com.sparta.hanghaemini.dto.PostRequestDto;
import com.sparta.hanghaemini.dto.PostResponseDto;
import com.sparta.hanghaemini.model.Category;
import com.sparta.hanghaemini.model.Post;
import com.sparta.hanghaemini.security.UserDetailsImpl;

import com.sparta.hanghaemini.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/api/posts")
    public JudgeSuccessDto addPost(@RequestBody PostRequestDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.addPost(postDto, userDetails);
    }

    // 모든 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 카테고리에 해당하는 게시글 조회
    @GetMapping("/api/category/{category}")
    public List<PostResponseDto> getCatPosts(@PathVariable String category) {
        return postService.getCatPosts(category);
    }

    // 게시글 상세 조회
    @GetMapping("/api/posts/{postId}")
    public PostCommentDto getDetail(@PathVariable Long postId) {
        return postService.getDetail(postId);
    }

    // 게시글 수정
    @PutMapping("/api/posts/{postId}")
    public JudgeSuccessDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(postId, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{postId}")
    public JudgeSuccessDto deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }

    @GetMapping("/api/posts/pageable")
    public Page<Post> pageablePost(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam String sortBy,
        @RequestParam boolean isAsc,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        page --;
        return postService.getPageablePost(page, size, sortBy, isAsc);
    }








    


    @GetMapping("/api/test")
    public void test() {
        System.out.println("HI");
//        System.out.println(a);
        System.out.println("HI");
    }
}
