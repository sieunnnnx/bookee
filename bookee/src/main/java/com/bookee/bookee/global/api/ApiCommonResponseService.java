package com.bookee.bookee.global.api;

import org.springframework.stereotype.Service;

@Service
public class ApiCommonResponseService {

    public <T> ApiCommonResponse<T> success(T data) {
        return ApiCommonResponse.<T>builder()
                .isSuccess(true)
                .status(SuccessCode.SUCCESS.getHttpStatus())
                .code(SuccessCode.SUCCESS.getCode())
                .message(SuccessCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public ApiCommonResponse<?> fail(BaseCode baseCode) {
        return ApiCommonResponse.builder()
                .isSuccess(false)
                .status(baseCode.getHttpStatus())
                .code(baseCode.getCode())
                .message(baseCode.getMessage())
                .data(null)
                .build();
    }
}
