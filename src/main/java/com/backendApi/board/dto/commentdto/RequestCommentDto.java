package com.backendApi.board.dto.commentdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RequestCommentDto {

    @NotBlank
    private String content;
}
