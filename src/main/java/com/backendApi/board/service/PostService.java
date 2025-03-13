package com.backendApi.board.service;

import com.backendApi.board.domain.Board;
import com.backendApi.board.domain.Member;
import com.backendApi.board.domain.Post;
import com.backendApi.board.dto.commentdto.ResponseCommentDto;
import com.backendApi.board.dto.postdto.RequestPostDto;
import com.backendApi.board.dto.postdto.ResponsePostDetailDto;
import com.backendApi.board.dto.postdto.ResponsePostDto;
import com.backendApi.board.repository.BoardRepository;
import com.backendApi.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    public final PostRepository postRepository;
    public final BoardRepository boardRepository;

    @Transactional
    public Post createPost(String title, RequestPostDto requestPostDto, Member member) {
        Post post = new Post(requestPostDto.getTitle(), requestPostDto.getContent(), member);
        Board board = boardRepository.findByTitle(title);
       // post.setMember(member);
        post.setBoard(board);
        postRepository.save(post);
        return  post;
    }

    public Post getPostById(Long id){
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public ResponsePostDto getPost(Long id){
        Post post = postRepository.findById(id);
        ResponsePostDto responsePostDetailDto = new ResponsePostDetailDto(id, post.getTitle(), post.getContent(), post.getMember().getNickname(),post.getCommentList().stream()
                .map(ResponseCommentDto::new)
                .collect(Collectors.toList()));

        return responsePostDetailDto;
    }

    @Transactional
    public void updatePost(Long id,RequestPostDto requestPostDto){
        Post post = postRepository.findById(id);
        post.updateTitle(requestPostDto.getTitle());
        post.updateContent(requestPostDto.getContent());
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id);
        postRepository.delete(post);

    }
}
