package com.backendApi.board.service;


import com.backendApi.board.domain.Board;
import com.backendApi.board.dto.boarddto.RequestBoardDto;
import com.backendApi.board.dto.boarddto.ResponseBoardDto;
import com.backendApi.board.dto.postdto.ResponsePostByGetBoardDto;
import com.backendApi.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(RequestBoardDto requestBoardDto) {
        Board board = new Board(requestBoardDto.getTitle());
        boardRepository.save(board);
        return board;
    }


    @Transactional(readOnly = true)
    public ResponseBoardDto getBoard(String title){
        Board board = boardRepository.findByTitle(title);
        ResponseBoardDto responseBoardDto = new ResponseBoardDto(board.getTitle(), board.getPostList().stream()
                .map(ResponsePostByGetBoardDto::new)
                .collect(Collectors.toList()));

        return  responseBoardDto;
    }

    @Transactional
    public void updateBoard(String title, RequestBoardDto requestBoardDto){
        Board board = boardRepository.findByTitle(title);
        board.updateTitle(requestBoardDto.getTitle());
    }

    @Transactional
    public void deleteBoard(String title){
        Board board = boardRepository.findByTitle(title);
        boardRepository.delete(board);

    }
}
