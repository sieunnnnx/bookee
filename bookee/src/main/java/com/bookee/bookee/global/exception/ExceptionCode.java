package com.bookee.bookee.global.exception;

import com.bookee.bookee.global.api.BaseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode implements BaseCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "99", "내부 오류가 발생했습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "98", "요청한 리소스를 찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "97", "잘못된 요청입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    ExceptionCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
