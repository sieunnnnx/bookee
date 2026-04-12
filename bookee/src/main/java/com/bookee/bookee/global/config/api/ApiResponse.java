package com.bookee.bookee.global.config.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {

    private boolean isSuccess;
    private String code;
    private T data;
    private String message;
}
