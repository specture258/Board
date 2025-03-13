package com.backendApi.board.repository;

import com.backendApi.board.domain.Board;
import com.backendApi.board.domain.Comment;
import com.backendApi.board.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    @PersistenceContext
    public final EntityManager em;


    public void save(Board board){
        em.persist(board);
    }

    public Board findById(Long id){
        return em.find(Board.class, id);
    }

    public Board findByTitle(String title){
        Board board = em.createQuery("select b from Board b where b.title=:title", Board.class)
                .setParameter("title", title)
                 .getSingleResult();

        return board;
    }

    public void delete(Board board) {
        em.remove(board);
    }

}
