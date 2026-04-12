package com.bookee.bookee.global.config.exception;

import com.bookee.bookee.global.config.api.ApiResponse;
import com.bookee.bookee.global.config.api.ApiResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ApiResponseService apiResponseService;

    @ExceptionHandler(CustomException.class)
    public ApiResponse<?> handlerCustomException(CustomException e) {
        return apiResponseService.fail(e.getErrorCode());
    }
}
