package com.bookee.bookee.global.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode implements BaseCode{

    SUCCESS(HttpStatus.OK, "00", "응답에 성공했습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    SuccessCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
