package com.backendApi.board.controller;

import com.backendApi.board.domain.Comment;
import com.backendApi.board.domain.Member;
import com.backendApi.board.dto.commentdto.RequestCommentDto;
import com.backendApi.board.dto.commentdto.ResponseCommentDto;
import com.backendApi.board.exception.ContentModifyException;
import com.backendApi.board.service.CommentService;
import com.backendApi.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.backendApi.board.exception.ErrorCode.NOT_AUTHORIZATION;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;


    @PostMapping("/board/post/{id}")
    public ResponseEntity<ResponseCommentDto> uploadComment(@Valid @RequestBody RequestCommentDto requestCommentDto, @PathVariable("id")Long id,
                                                         @SessionAttribute("loginMember") Member member){

        Comment comment = commentService.createComment(id, requestCommentDto, member);
        ResponseCommentDto responseCommentDto = new ResponseCommentDto( comment.getContent(), comment.getMember().getNickname());
        return new ResponseEntity<>(responseCommentDto, HttpStatus.OK);
    }

    @PutMapping("/board/post/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseCommentDto> updateComment(@Valid @RequestBody RequestCommentDto requestCommentDto, @PathVariable("postId") Long postId, @PathVariable ("commentId") Long commentId,
                                                            @SessionAttribute("loginMember") Member member){
        ResponseCommentDto comment = commentService.getComment(commentId);
        if(!member.getNickname().equals(comment.getNickname())){
            throw new ContentModifyException(NOT_AUTHORIZATION);
        }
        commentService.updateComment(commentId, requestCommentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/board/post/{postId}/comment/{commentId}")
    public void deleteComment(@PathVariable("postId") Long postId, @PathVariable ("commentId") Long commentId,
                              @SessionAttribute("loginMember") Member member  ){

        ResponseCommentDto comment = commentService.getComment(commentId);
        if(!member.getNickname().equals(comment.getNickname())){
            throw new ContentModifyException(NOT_AUTHORIZATION);
        }
        commentService.deleteComment(commentId);
    }






}
