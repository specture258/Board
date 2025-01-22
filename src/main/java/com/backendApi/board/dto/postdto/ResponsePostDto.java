package com.backendApi.board.dto.postdto;

import com.backendApi.board.dto.commentdto.ResponseCommentDto;
import lombok.Data;

import java.util.List;

@Data

public class ResponsePostDto {

    private Long id;

    private String title;

    private String content;

    private String nickname;

    public ResponsePostDto(Long id, String title, String content, String nickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }

    private List<ResponseCommentDto> commentList;

    public ResponsePostDto(Long id, String title, String content, String nickname, List<ResponseCommentDto> commentList){
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.commentList = commentList;
    }





}
