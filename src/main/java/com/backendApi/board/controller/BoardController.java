package com.backendApi.board.controller;

import com.backendApi.board.domain.Board;
import com.backendApi.board.dto.boarddto.RequestBoardDto;
import com.backendApi.board.dto.boarddto.ResponseBoardDto;
import com.backendApi.board.dto.postdto.ResponsePostDto;
import com.backendApi.board.service.BoardService;
import com.backendApi.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    public final PostService postService;
    private final BoardService boardService;


    // 게시판 하나에 있는 글의 제목과 작성자 조회
    @GetMapping("/board/{title}")
    public ResponseEntity<ResponseBoardDto> getBoard(@PathVariable String title){
        ResponseBoardDto board = boardService.getBoard(title);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    //생성 //제목만 정해서 생성하면됨
    @PostMapping("/board/management")
    public ResponseEntity<ResponseBoardDto> createBoard(@Valid @RequestBody RequestBoardDto requestBoardDto){
        Board board = boardService.createBoard(requestBoardDto);

        ResponseBoardDto responseBoardDto = new ResponseBoardDto(board.getTitle());

        return new ResponseEntity<>(responseBoardDto, HttpStatus.OK);
        //TODO 응답값 수정 필요

    }


    //수정  //제목만 수정
    @PutMapping("/board/management/{title}")
    public ResponseEntity<ResponsePostDto> updateBoard(@Valid @RequestBody RequestBoardDto requestBoardDto, @PathVariable("title") String title){

        boardService.updateBoard(title, requestBoardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/board/management/{title}")
    public void deleteBoard (@PathVariable("title") String title){

        boardService.deleteBoard(title);
    }
}
