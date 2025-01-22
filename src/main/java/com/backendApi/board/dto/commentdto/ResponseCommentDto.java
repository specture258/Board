package com.backendApi.board.dto.commentdto;

import com.backendApi.board.domain.Comment;
import lombok.Getter;

@Getter
public class ResponseCommentDto {

    private String content;

    private String nickname;

    public ResponseCommentDto(Comment comment) {
        this.content = comment.getContent();
        this.nickname = comment.getMember().getNickname();
    }

    public ResponseCommentDto(String content, String nickname) {
        this.content = content;
        this.nickname = nickname;
    }


}
