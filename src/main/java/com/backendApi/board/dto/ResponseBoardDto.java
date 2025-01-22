package com.backendApi.board.dto;

import com.backendApi.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseBoardDto {

    Long id;

    String title;

    String nickname;


    public ResponseBoardDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getMember().getNickname();
    }
}
