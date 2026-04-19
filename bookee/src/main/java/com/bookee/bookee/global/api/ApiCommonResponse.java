package com.bookee.bookee.global.api;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiCommonResponse<T> {

    private boolean isSuccess;

    private HttpStatus status;

    private String code;

    private T data;

    private String message;
}
