package com.backendApi.board.controller;


import com.backendApi.board.domain.Member;
import com.backendApi.board.domain.Post;
import com.backendApi.board.dto.postdto.RequestPostDto;
import com.backendApi.board.dto.postdto.ResponsePostDto;
import com.backendApi.board.dto.postdto.ResponseUploadPostDto;
import com.backendApi.board.exception.ContentModifyException;
import com.backendApi.board.service.BoardService;
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
    public final BoardService boardService;

    @GetMapping("/board/post")
    public ResponseEntity<Void> getWritingPostPage(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/board/{title}/post")
    public ResponseEntity<ResponsePostDto> uploadPost(@Valid @RequestBody RequestPostDto requestPostDto, @PathVariable("title")String title,
                                                      @SessionAttribute("loginMember") Member member){

        Post post = postService.createPost(title, requestPostDto, member);

        ResponsePostDto responsePostDetailDto = new ResponseUploadPostDto(post.getBoard().getTitle(), post.getId(), post.getTitle(), post.getContent(), member.getNickname());

        return new ResponseEntity<>(responsePostDetailDto, HttpStatus.OK);
    }

    @GetMapping("/board/{title}/post/{id}")
    public ResponseEntity<ResponsePostDto> getPostDetail(@PathVariable("id")Long id, @PathVariable("title")String title){

        ResponsePostDto postDetail = postService.getPost(id);
        return new ResponseEntity<>(postDetail, HttpStatus.OK);

    }

    @DeleteMapping("/board/{title}/post/{id}")
    public void deletePostDetail(@PathVariable("id")Long id,@SessionAttribute("loginMember") Member member){

        ResponsePostDto post = postService.getPost(id);

        if(!member.getNickname().equals(post.getNickname())){
            throw new ContentModifyException(NOT_AUTHORIZATION);
        }
        postService.deletePost(id);
    }

    @PutMapping("/board/{title}/post/{id}")
    public ResponseEntity<ResponsePostDto> updatePostDetail(@Valid @RequestBody RequestPostDto requestPostDto,
                                                            @PathVariable("id")Long id, @SessionAttribute("loginMember") Member member){
        ResponsePostDto post = postService.getPost(id);

        if(!member.getNickname().equals(post.getNickname())){
            throw new ContentModifyException(NOT_AUTHORIZATION);
        }
        postService.updatePost(id, requestPostDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
