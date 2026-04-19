package com.bookee.bookee.global.exception;

import com.bookee.bookee.global.api.ApiCommonResponse;
import com.bookee.bookee.global.api.ApiCommonResponseService;
import com.bookee.bookee.global.api.BaseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ApiCommonResponseService apiCommonResponseService;

    /**
     * 비즈니스 예외
     */
    @ExceptionHandler(CustomException.class)
    public ApiCommonResponse<?> handleCustomException(BaseCode e) {

        return apiCommonResponseService.fail(e);
    }

    /**
     * 잘못된 요청
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiCommonResponse<?> handleBadRequest() {

        return apiCommonResponseService.fail(GlobalException.BAD_REQUEST);
    }

    /**
     * 리소스 없음
     */
    @ExceptionHandler(java.util.NoSuchElementException.class)
    public ApiCommonResponse<?> handleNotFound() {

        return apiCommonResponseService.fail(GlobalException.NOT_FOUND);
    }

    /**
     * 예상하지 못한 서버 에러
     */
    @ExceptionHandler(Exception.class)
    public ApiCommonResponse<?> handleException() {

        return apiCommonResponseService.fail(GlobalException.INTERNAL_SERVER_ERROR);
    }
}
