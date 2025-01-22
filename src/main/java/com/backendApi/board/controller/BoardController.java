package com.backendApi.board.controller;

import com.backendApi.board.dto.ResponseBoardDto;
import com.backendApi.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    public final PostService postService;

//    @GetMapping("/board")
//    public List<ResponseBoardDto> getBoard(){
//        return postService.getAllPost();
//    }

    @GetMapping("/board")
    public ResponseEntity<List<ResponseBoardDto>> getBoard(){
        List<ResponseBoardDto> allPost = postService.getAllPost();
        return  new ResponseEntity<>(allPost, HttpStatus.OK);

    }
}
