package com.example.zerobase.web.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZerobaseException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public ExceptionCode getCode() {
        return exceptionCode;
    }
}
