package com.backendApi.board.dto.boarddto;

import com.backendApi.board.dto.postdto.ResponsePostByGetBoardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseBoardDto {

    String title;

    private List<ResponsePostByGetBoardDto> postList;

    public ResponseBoardDto(String title) {
        this.title = title;
    }
}
