package com.backendApi.board.controller;


import com.backendApi.board.domain.Member;
import com.backendApi.board.domain.Post;
import com.backendApi.board.dto.postdto.RequestPostDto;
import com.backendApi.board.dto.postdto.ResponsePostDto;
import com.backendApi.board.exception.ContentModifyException;
import com.backendApi.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.backendApi.board.exception.ErrorCode.NOT_AUTHORIZATION;


@RestController
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @GetMapping("/board/post")
    public ResponseEntity<Void> getWritingPostPage(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/board/post")
    public ResponseEntity<ResponsePostDto> uploadPost(@Valid @RequestBody RequestPostDto requestPostDto,
                                                      @SessionAttribute("loginMember") Member member){

        Post post = postService.createPost(requestPostDto, member);

        ResponsePostDto responsePostDto = new ResponsePostDto(post.getId(), post.getTitle(), post.getContent(), member.getNickname());

        return new ResponseEntity<>(responsePostDto, HttpStatus.OK);
    }

    @GetMapping("/board/post/{id}")
    public ResponseEntity<ResponsePostDto> getPostDetail(@PathVariable("id")Long id){

        ResponsePostDto postDetail = postService.getPost(id);
        return new ResponseEntity<>(postDetail, HttpStatus.OK);

    }

    @DeleteMapping("/board/post/{id}")
    public void deletePostDetail(@PathVariable("id")Long id,@SessionAttribute("loginMember") Member member){

        ResponsePostDto post = postService.getPost(id);

        if(!member.getNickname().equals(post.getNickname())){
            throw new ContentModifyException(NOT_AUTHORIZATION);
        }
        postService.deletePost(id);
    }

    @PutMapping("/board/post/{id}")
    public ResponseEntity<ResponsePostDto> updatePostDetail( @Valid @RequestBody RequestPostDto requestPostDto,
                                                             @PathVariable("id")Long id,@SessionAttribute("loginMember") Member member){
        ResponsePostDto post = postService.getPost(id);

        if(!member.getNickname().equals(post.getNickname())){
            throw new ContentModifyException(NOT_AUTHORIZATION);
        }
        postService.updatePost(id, requestPostDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
