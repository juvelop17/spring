package com.example.zerobase.web.advice;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResult {
    private String code;
    private String message;
}
