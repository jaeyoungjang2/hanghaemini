package com.sparta.hanghaemini.model;

import com.sparta.hanghaemini.dto.CommentRequestDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        post.getComments().add(this);
        this.user = user;
    }

    public void update(CommentRequestDto commentDto) {
        this.content = commentDto.getContent();
    }
}
