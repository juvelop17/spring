package com.example.zerobase.web.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    // TODO : 익셉션 핸들러를 작성하세요

    @ExceptionHandler(value = ZerobaseException.class)
    public ResponseEntity<ErrorResult> runtimeException(ZerobaseException e) {
        ExceptionCode errorCode = e.getExceptionCode();
        ErrorResult result = ErrorResult.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<ErrorResult>(result, errorCode.getStatus());
    }

}
