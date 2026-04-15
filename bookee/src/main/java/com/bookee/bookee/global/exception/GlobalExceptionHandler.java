package com.bookee.bookee.global.exception;

import com.bookee.bookee.global.api.ApiResponse;
import com.bookee.bookee.global.api.ApiResponseService;
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
