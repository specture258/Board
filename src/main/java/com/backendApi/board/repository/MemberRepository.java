package com.backendApi.board.repository;

import com.backendApi.board.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    public final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Optional<Member> findByUserName(String username){
        List<Member> member = em.createQuery("select m from Member m where m.username=:username", Member.class)
                .setParameter("username", username)
                .getResultList();
        return member.stream().findAny();
    }

    public Optional<Member> findByNickName(String nickname){
        List<Member> member = em.createQuery("select m from Member m where m.nickname=:nickname", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return member.stream().findAny();
    }




}
