package com.bookee.bookee.global.config.api;

import com.bookee.bookee.global.config.exception.GlobalErrorCode;
import org.springframework.stereotype.Service;

@Service
public class ApiResponseService {
    private final String SUCCESS_CODE = "00";
    private final String SUCCESS_MESSAGE = "Request was successful.";


    public <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code(SUCCESS_CODE)
                .data(data)
                .message(SUCCESS_MESSAGE)
                .build();
    }

    public ApiResponse<?> fail(GlobalErrorCode errorCode) {
        return ApiResponse.builder()
                .isSuccess(false)
                .code(errorCode.getCode())
                .data(null)
                .message(errorCode.getMessage())
                .build();
    }
}
