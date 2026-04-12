package com.bookee.bookee.global.config.exception;

import com.bookee.bookee.global.config.api.ApiResponse;
import com.bookee.bookee.global.config.api.ApiResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ApiResponseService apiResponseService;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<?>> handlerCustomException(CustomException e) {

        ExceptionCode exceptionCode = e.getExceptionCode();

        return ResponseEntity
                .status(exceptionCode.getHttpStatus())
                .body(apiResponseService.fail(exceptionCode));
    }
}
