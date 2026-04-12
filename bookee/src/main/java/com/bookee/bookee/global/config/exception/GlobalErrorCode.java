package com.bookee.bookee.global.config.exception;

import lombok.Getter;

@Getter
public enum GlobalErrorCode {

    INTERNAL_SERVER_ERROR("99", "A server error occurred."),
    INVALID_REQUEST("98", "This is an invalid request.");

    private final String code;
    private final String message;

    GlobalErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
