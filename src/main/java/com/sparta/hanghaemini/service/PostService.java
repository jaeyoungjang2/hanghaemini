package com.sparta.hanghaemini.service;

import com.sparta.hanghaemini.dto.*;
import com.sparta.hanghaemini.model.*;
import com.sparta.hanghaemini.repository.*;
import com.sparta.hanghaemini.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
//    private final UserRepository userRepository;

    // 게시글 작성
    @Transactional
    public JudgeSuccessDto addPost(PostRequestDto postDto, UserDetailsImpl userDetails) {
        String title = postDto.getTitle();
        String content = postDto.getContent();
        TeamDto teamDto = postDto.getTeam();
        LocalDateTime createdAt = postDto.getCreatedAt();
        CategoryDto categoryDto = postDto.getCategory();

        String categoryName = categoryDto.getName();
        Long maxTeamOf = teamDto.getMaxTeamOf();
        Long curTeamCnt = teamDto.getCurTeamCnt();

        if ((title == null || title.trim().isEmpty())
                || (content == null || content.trim().isEmpty())) {
            boolean ok = false;
            String message = "게시글 저장 실패";

            return new JudgeSuccessDto(ok, message);
        }

        User user = userDetails.getUser();

        // 카테고리 저장
        Category category = new Category(categoryName);
        categoryRepository.save(category);

        // 최대 인원 저장
        Team team = new Team(maxTeamOf, curTeamCnt);
        teamRepository.save(team);

        // 게시글 저장
        Post post = new Post(title, content, user, team, createdAt, category);
        postRepository.save(post);

        boolean ok = true;
        String message = "게시글 저장 완료";

        return new JudgeSuccessDto(ok, message);
    }

    // 게시글 상세 조회
    public PostCommentDto getDetail(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("id가 존재하지 않습니다."));

        Team team = post.getTeam();
        TeamDto teamDto = new TeamDto(team);

        Category category = post.getCategory();
        CategoryDto categoryDto = new CategoryDto(category);

        List<Comment> comments = commentRepository.findByPostId(postId);

        List<CommentResponseDto> commentDto = new ArrayList<>();

        for (Comment comment : comments) {

            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentDto.add(commentResponseDto);
        }

        return new PostCommentDto(post, teamDto, categoryDto, commentDto);
    }

    // 게시글 전체 조회
    public List<PostResponseDto> getPosts() {
        // 보내게 될 게시글 리스트
        List<PostResponseDto> postAllList = new ArrayList<>();

        // DB에 저장된 게시글들 불러옴
        List<Post> postList = postRepository.findAll();

        // 게시글 하나씩
        for (Post post : postList) {

            Team team = post.getTeam();
            TeamDto teamDto = new TeamDto(team);

            Category category = post.getCategory();
            CategoryDto categoryDto = new CategoryDto(category);

            PostResponseDto postDto = new PostResponseDto(post, teamDto, categoryDto);
            postAllList.add(postDto);
        }

        return postAllList;
    }

    // 카테고리에 해당하는 게시글 조회
    public List<PostResponseDto> getCatPosts(String category) {
        // 보내게 될 카테고리 게시글 리스트
        List<PostResponseDto> postCatList = new ArrayList<>();
        List<Post> postList = postRepository.findByCategoryName(category);

        for (Post post : postList) {

            Team team = post.getTeam();
            TeamDto teamDto = new TeamDto(team);

//            String name = category.getName();
            CategoryDto categoryDto = new CategoryDto(category);

            PostResponseDto postDto = new PostResponseDto(post, teamDto, categoryDto);
            postCatList.add(postDto);
        }
        return postCatList;
    }

    // 게시글 수정
    @Transactional
    public JudgeSuccessDto updatePost(Long postId, PostRequestDto requestDto) {
        // post를 불러옴
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 변경 가능한 값(변경할  값)을 가져옴
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        Long maxTeamOf = requestDto.getTeam().getMaxTeamOf();
        String name = requestDto.getCategory().getName();

        // 현재 정원 가져오기
        Team team = post.getTeam();
        // 현재 카테고리 가져오기
        Category category = post.getCategory();
        // 현재 인원 가져오기
        Long curTeamCnt = post.getTeam().getCurTeamCnt();

        // 현재 인원과 변경할 정원 비교
        if (curTeamCnt > maxTeamOf) {
            boolean ok = false;
            String message = "현재 인원이 최대 인원보다 큽니다.";

            return new JudgeSuccessDto(ok, message);
        }

        team.changeMax(maxTeamOf);
        category.changeCat(name);

        if ((title == null || title.trim().isEmpty())
                || (content == null || content.trim().isEmpty())) {
            boolean ok = false;
            String message = "게시글 수정 실패";

            return new JudgeSuccessDto(ok, message);
        }

        post.update(requestDto, team, category);

        boolean ok = true;
        String message = "게시글 수정 완료";

        return new JudgeSuccessDto(ok, message);
    }

    // 게시글 삭제
    public JudgeSuccessDto deletePost(Long postId) {
        if (postId == null) {
            boolean ok = false;
            String message = "게시글 삭제 실패";

            return new JudgeSuccessDto(ok, message);
        }
        postRepository.deleteById(postId);

        boolean ok = true;
        String message = "게시글 삭제 완료";

        return new JudgeSuccessDto(ok, message);
    }
}