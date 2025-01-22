package com.backendApi.board.service;


import com.backendApi.board.domain.Comment;
import com.backendApi.board.domain.Member;
import com.backendApi.board.domain.Post;
import com.backendApi.board.dto.commentdto.RequestCommentDto;
import com.backendApi.board.dto.commentdto.ResponseCommentDto;
import com.backendApi.board.dto.postdto.RequestPostDto;
import com.backendApi.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
    public Comment createComment(Long id, RequestCommentDto requestCommentDto, Member member) {
        Post post = postService.getPostById(id);
        Comment comment = new Comment(requestCommentDto.getContent(), member);
        comment.setPost(post);
        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public ResponseCommentDto getComment(Long id){
        Comment comment = commentRepository.findById(id);
        ResponseCommentDto responseCommentDto = new ResponseCommentDto(comment.getContent(), comment.getMember().getNickname());
        return  responseCommentDto;
    }

    @Transactional
    public void updateComment(Long id, RequestCommentDto requestCommentDto){
        Comment comment = commentRepository.findById(id);
        comment.updateContent(requestCommentDto.getContent());
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id);
        commentRepository.delete(comment);
    }
}
