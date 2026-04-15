package com.bookee.bookee.global.api;

import org.springframework.stereotype.Service;

@Service
public class ApiResponseService {

    public <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code(SuccessCode.SUCCESS.getCode())
                .message(SuccessCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public ApiResponse<?> fail(BaseCode baseCode) {
        return ApiResponse.builder()
                .isSuccess(false)
                .code(baseCode.getCode())
                .message(baseCode.getMessage())
                .data(null)
                .build();
    }
}
