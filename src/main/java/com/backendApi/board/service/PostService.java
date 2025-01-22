package com.backendApi.board.service;

import com.backendApi.board.domain.Member;
import com.backendApi.board.domain.Post;
import com.backendApi.board.dto.commentdto.ResponseCommentDto;
import com.backendApi.board.dto.postdto.RequestPostDto;
import com.backendApi.board.dto.ResponseBoardDto;
import com.backendApi.board.dto.postdto.ResponsePostDto;
import com.backendApi.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static com.backendApi.board.exception.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {

    public final PostRepository postRepository;

    @Transactional
    public Post createPost(RequestPostDto requestPostDto, Member member) {
        Post post = new Post(requestPostDto.getTitle(), requestPostDto.getContent(), member);
       // post.setMember(member);
        postRepository.save(post);
        return  post;
    }

    public Post getPostById(Long id){
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ResponseBoardDto> getAllPost(){
//        List<ResponseBoardDto> list = new ArrayList<>();
//        Long count = postRepository.findPostCount();
//
//        for(long i=1 ; i<=count ;i++){
//            Post post = getPostById(i);
//            list.add(new ResponseBoardDto(post.getId(), post.getTitle(), post.getMember().getNickname()));
//        }

        return postRepository.findAllPost().stream()
                .map(ResponseBoardDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResponsePostDto getPost(Long id){
        Post post = postRepository.findById(id);
        ResponsePostDto responsePostDto = new ResponsePostDto(id, post.getTitle(), post.getContent(), post.getMember().getNickname(),post.getCommentList().stream()
                .map(ResponseCommentDto::new)
                .collect(Collectors.toList()));

        return  responsePostDto;
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
