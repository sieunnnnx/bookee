package com.bookee.bookee.global.config.api;

import com.bookee.bookee.global.config.exception.ExceptionCode;
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

    public ApiResponse<?> fail(ExceptionCode errorCode) {
        return ApiResponse.builder()
                .isSuccess(false)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }
}
