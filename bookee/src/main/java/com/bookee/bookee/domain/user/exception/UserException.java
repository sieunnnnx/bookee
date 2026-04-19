package com.bookee.bookee.domain.user.exception;

import com.bookee.bookee.global.api.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserException implements BaseCode {

    LOGIN_IN_ALREADY_EXIST(HttpStatus.CONFLICT, "AUTH-01", "이미 존재하는 아이디 입니다."),
    NICKNAME_ALREADY_EXIST(HttpStatus.CONFLICT, "AUTH-02", "이미 사용중인 닉네임 입니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "AUTH-03", "아이디 또는 비밀번호가 올바르지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH-04", "유저가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
