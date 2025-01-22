package com.backendApi.board.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        ErrorCode errorCode = ErrorCode.NO_PARAMETER;
        if(request.getRequestURL().toString().equals("http://localhost:8080/board/signin")){
             errorCode = ErrorCode.NO_SIGNIN_INFORMATION;
        }else if(request.getRequestURL().toString().equals("http://localhost:8080/board/signup")){
             errorCode = ErrorCode.NO_SIGNUP_INFORMATION;
        }

        return handleExceptionInternal(e, errorCode);
    }

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<Object> handlerSignupException(SignupException e){
        ErrorCode errorCode = e.getErrorCode();
        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(SigninException.class)
    public ResponseEntity<Object> handlerSigninException(SigninException e){
        ErrorCode errorCode = e.getErrorCode();
        return  handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handlerNotFoundException(NoHandlerFoundException e){
        return  handle404ExceptionInternal(e);
    }

    @ExceptionHandler(ContentModifyException.class)
    public ResponseEntity<Object> handlerAuthorizationException(ContentModifyException e){
        ErrorCode errorCode = e.getErrorCode();
        return  handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> handlerAuthorizationException(AuthorizationException e){
        ErrorCode errorCode = e.getErrorCode();
        return  handleExceptionInternal(errorCode);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResult(errorCode));
    }


    private ResponseEntity<Object> handleExceptionInternal(BindException e, ErrorCode errorCode){
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResult(e, errorCode));
    }

    private ResponseEntity<Object> handle404ExceptionInternal(NoHandlerFoundException e){
        return ResponseEntity.status(ErrorCode.NOT_FOUND.getHttpStatus())
                .body(make404ErrorResult(e));
    }

    private ErrorResult make404ErrorResult(NoHandlerFoundException e){
        return ErrorResult.builder()
                .code(String.valueOf(ErrorCode.NOT_FOUND.getHttpStatus()))
                .message(ErrorCode.NOT_FOUND.getMessage() + e.getRequestURL())
                .build();
    }

    private ErrorResult makeErrorResult(ErrorCode errorCode){
        return ErrorResult.builder()
                .code(String.valueOf(errorCode.getHttpStatus()))
                .message(errorCode.getMessage())
                .build();
    }


    private ErrorResult makeErrorResult(BindException e, ErrorCode errorCode){
        List<ErrorResult.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResult.ValidationError::of)
                .toList();

        return ErrorResult.builder()
                .code(String.valueOf(errorCode.getHttpStatus()))
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
}
