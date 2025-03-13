package com.backendApi.board.interceptor;

import com.backendApi.board.domain.Member;
import com.backendApi.board.exception.AuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.backendApi.board.exception.ErrorCode.NOT_AUTHORIZATION;

public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler) throws Exception{

        HttpSession session = request.getSession(false);
        Member adminMember = (Member) session.getAttribute("loginMember");
        if(!adminMember.getNickname().equals("canterbury")){

            throw  new AuthorizationException(NOT_AUTHORIZATION);
        }
        return true;
    }

}
