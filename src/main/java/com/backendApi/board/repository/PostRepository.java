package com.backendApi.board.repository;

import com.backendApi.board.domain.Member;
import com.backendApi.board.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    @PersistenceContext
    public final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public Post findById(Long id){
        return em.find(Post.class, id);
    }

    public List<Post> findAllPost() {
        List<Post> post = em.createQuery("select p from Post p", Post.class)
                .getResultList();
        return post;
    }

    public Long findPostCount(){
        return em.createQuery("select count(p) from Post p", Long.class).getSingleResult();
    }

    public void delete(Post post){
        em.remove(post);
    }

}
