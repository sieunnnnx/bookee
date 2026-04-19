package com.bookee.bookee.global.exception;

import com.bookee.bookee.global.api.ApiResponse;
import com.bookee.bookee.global.api.ApiResponseService;
import com.bookee.bookee.global.api.BaseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ApiResponseService apiResponseService;

    /**
     * 비즈니스 예외
     */
    @ExceptionHandler(CustomException.class)
    public ApiResponse<?> handleCustomException(BaseCode e) {

        return apiResponseService.fail(e);
    }

    /**
     * 잘못된 요청
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(Exception e) {

        return ResponseEntity.ok(
                apiResponseService.fail(GlobalException.BAD_REQUEST)
        );
    }

    /**
     * 리소스 없음
     */
    @ExceptionHandler(java.util.NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(Exception e) {

        return ResponseEntity.ok(
                apiResponseService.fail(GlobalException.NOT_FOUND)
        );
    }

    /**
     * 예상하지 못한 서버 에러
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {

        e.printStackTrace();

        return ResponseEntity
                .status(GlobalException.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(apiResponseService.fail(GlobalException.INTERNAL_SERVER_ERROR));
    }
}
