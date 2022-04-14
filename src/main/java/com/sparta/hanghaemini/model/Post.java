package com.sparta.hanghaemini.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.sparta.hanghaemini.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    // 게시글을 쓴 작성자
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 스터디 참여 그룹
    @OneToOne()
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    public Post(String title, String content, User user, Team team, LocalDateTime createdAt, Category category) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.team = team;
        this.category = category;
        this.createdAt = createdAt;
        this.category.getPosts().add(this);
    }

    public void update(PostRequestDto postDto, Team team, Category category) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.team = team;
        this.category = category;
    }
}