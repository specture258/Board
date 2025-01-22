package com.backendApi.board.dto.postdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestPostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;


}
