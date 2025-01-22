package com.backendApi.board.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ContentModifyException extends RuntimeException{

    private final ErrorCode errorCode;
}
