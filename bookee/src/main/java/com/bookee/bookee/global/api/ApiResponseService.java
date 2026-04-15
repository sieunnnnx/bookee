package com.bookee.bookee.global.api;

import com.bookee.bookee.global.exception.ExceptionCode;
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

    public ApiResponse<?> fail(ExceptionCode exceptionCode) {
        return ApiResponse.builder()
                .isSuccess(false)
                .code(exceptionCode.getCode())
                .message(exceptionCode.getMessage())
                .data(null)
                .build();
    }
}
