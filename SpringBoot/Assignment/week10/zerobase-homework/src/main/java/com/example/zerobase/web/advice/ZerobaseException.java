package com.example.zerobase.web.advice;

import lombok.Getter;

@Getter
public class ZerobaseException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public ZerobaseException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ZerobaseException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ZerobaseException(String message, Throwable cause, ExceptionCode exceptionCode) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public ZerobaseException(Throwable cause, ExceptionCode exceptionCode) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public ZerobaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionCode exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
    }
}
