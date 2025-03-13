package com.backendApi.board.service;

import com.backendApi.board.domain.Grade;
import com.backendApi.board.domain.Member;
import com.backendApi.board.exception.SigninException;
import com.backendApi.board.exception.SignupException;
import com.backendApi.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.backendApi.board.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    static final String ADMIN_NICKNAME = "canterbury";

    public final MemberRepository memberRepository;

    public boolean isSameUserName(String username){
        Optional<Member> member = memberRepository.findByUserName(username);
        return member.isPresent();
    }

    public boolean isSameNickName(String nickname){
        Optional<Member> member = memberRepository.findByNickName(nickname);
        return member.isPresent();
    }


    @Transactional
    public void join(Member member){
        if(isSameUserName(member.getUsername())){
            throw new SignupException(DUPLICATED_PARAMETER);
        }else if(isSameNickName(member.getNickname())){
            throw new SignupException(DUPLICATED_PARAMETER);
        }

        if(member.getNickname().equals(ADMIN_NICKNAME)){
            member.setAdmin();
        }
        memberRepository.save(member);
    }


    public Optional<Member> login(String username, String password) {
        Optional<Member> member = memberRepository.findByUserName(username);

        if(member.isEmpty()){
            throw new SigninException(MISMATCH_INFORMATION);
        }

        if(!password.equals(member.get().getPassword())){
            throw new SigninException(MISMATCH_INFORMATION);
        }
        return member;
    }
}
