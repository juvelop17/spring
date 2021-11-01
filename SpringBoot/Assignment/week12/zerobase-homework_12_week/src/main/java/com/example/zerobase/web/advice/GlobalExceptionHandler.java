package com.example.zerobase.web.advice;

import com.example.zerobase.web.exception.ExceptionCode;
import com.example.zerobase.web.exception.ZerobaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ZerobaseException.class)
    public ResponseEntity<ErrorResult> error(ZerobaseException exception) {

        ExceptionCode code = exception.getCode();
        ErrorResult errorResult = ErrorResult
                .builder()
                .code(code.name())
                .message(code.getMessage())
                .build();
        return new ResponseEntity(errorResult, code.getStatus());
    }
}
