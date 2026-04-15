package com.bookee.bookee.global.api;

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
