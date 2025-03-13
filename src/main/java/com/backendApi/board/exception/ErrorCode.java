package com.backendApi.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {


    NO_PARAMETER(HttpStatus.BAD_REQUEST, "내용을 입력해주세요"),
    DUPLICATED_PARAMETER(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디 혹은 닉네임 입니다"),
    MISMATCH_INFORMATION(HttpStatus.UNAUTHORIZED, "아이디 혹은 비밀번호가 올바르지 않습니다"),
    ANONYMOUS_ACCESS(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
    NO_SIGNUP_INFORMATION(HttpStatus.BAD_REQUEST, "회원 가입에 필요한 정보를 입력해주세요"),
    NO_SIGNIN_INFORMATION(HttpStatus.BAD_REQUEST, "아이디 / 비밀번호를 입력해주세요"),
    NOT_AUTHORIZATION(HttpStatus.FORBIDDEN, "수정 / 삭제 권한이 없습니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "페이지 / 리소스가 존재하지 않습니다 : ");



    private final HttpStatus httpStatus;
    private final String message;
}
