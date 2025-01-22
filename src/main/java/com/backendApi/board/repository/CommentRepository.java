package com.backendApi.board.repository;

import com.backendApi.board.domain.Comment;
import com.backendApi.board.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    @PersistenceContext
    public final EntityManager em;

    public void save(Comment comment){
        em.persist(comment);
    }

    public Comment findById(Long id){
        return em.find(Comment.class, id);
    }

    public void delete(Comment comment) {
        em.remove(comment);
    }
}
