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
            //response.sendRedirect("/board/signin?redirectURL=" + requestURI);    //todo 수정 필요 백엔드에서 처리하는게 아니라 그냥 오류 발생시키고 상태코드 반환
           // return false;
        }
        return true;
    }


}
