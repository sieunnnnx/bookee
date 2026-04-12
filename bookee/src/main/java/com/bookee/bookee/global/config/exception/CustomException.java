package com.bookee.bookee.global.config.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final GlobalErrorCode errorCode;

    public CustomException(GlobalErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
