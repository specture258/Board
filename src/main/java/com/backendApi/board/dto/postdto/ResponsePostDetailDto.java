package com.backendApi.board.dto.postdto;

import com.backendApi.board.dto.commentdto.ResponseCommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponsePostDetailDto implements ResponsePostDto {

    private Long id;

    private String title;

    private String content;

    private String nickname;

    private List<ResponseCommentDto> commentList;
}
