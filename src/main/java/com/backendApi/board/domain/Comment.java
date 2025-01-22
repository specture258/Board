package com.backendApi.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String content, Member member) {
        this.content = content;
        this.member = member;
    }


    public void setPost(Post post){
        if(this.post != null){
            this.post.getCommentList().remove(this);
        }
        this.post = post;
        post.getCommentList().add(this);
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
