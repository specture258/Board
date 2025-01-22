package com.backendApi.board.controller;

import com.backendApi.board.domain.Member;
import com.backendApi.board.dto.memberdto.*;
import com.backendApi.board.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    public final MemberService memberService;

    @GetMapping("/board/signup")
    public ResponseEntity<Void> getSignupPage(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/board/signin")
    public ResponseEntity<?> getSigninPage(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/board/signup")
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto){

        Member member = new Member(requestDto.getUsername(), requestDto.getPassword(), requestDto.getNickname());

        memberService.join(member);
        SignupResponseDto responseDto = new SignupResponseDto(requestDto.getUsername(), requestDto.getNickname());
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PostMapping("/board/signin")
    public ResponseEntity<SigninResponseDto> signin(@Valid @RequestBody SigninRequestDto requestDto, HttpServletRequest request){
        Optional<Member> optionalMember = memberService.login(requestDto.getUsername(), requestDto.getPassword());
        Member member = optionalMember.get();
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", member);
        String responseURL = "http://localhost:8080/board";
        SigninResponseDto responseDto = new SigninResponseDto(requestDto.getUsername(), member.getNickname(),responseURL);

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}",
                        name, session.getAttribute(name)));
        log.info("sessionId={}",session.getId());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/board/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("로그아웃 되었습니다");
    }
}
