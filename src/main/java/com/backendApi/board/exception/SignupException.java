package com.backendApi.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupException extends  RuntimeException{

    private final ErrorCode errorCode;
}
