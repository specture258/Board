package com.backendApi.board.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseUploadPostDto implements ResponsePostDto {
    private String boardTitle;

    private Long id;

    private String title;

    private String content;

    private String nickname;
}
