package com.backendApi.board.interceptor;

import com.backendApi.board.exception.AuthorizationException;
import com.backendApi.board.exception.SigninException;
import com.backendApi.board.exception.SignupException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

import static com.backendApi.board.exception.ErrorCode.ANONYMOUS_ACCESS;
import static com.backendApi.board.exception.ErrorCode.DUPLICATED_PARAMETER;

public class LoginCheckInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler) throws Exception{
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("loginMember") == null){

            throw  new AuthorizationException(ANONYMOUS_ACCESS);
        }
        return true;
    }


}
