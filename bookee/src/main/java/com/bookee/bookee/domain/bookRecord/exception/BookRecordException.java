package com.bookee.bookee.domain.bookRecord.exception;

import com.bookee.bookee.global.api.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookRecordException implements BaseCode {

    BOOK_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "BOOK-RECORD-01", "해당 독서록 기록을 찾을 수 없습니다."),
    INVALID_FINISHED_DATE(HttpStatus.BAD_REQUEST, "BOOK-RECORD-02", "완독 날짜를 입력할 수 없습니다. 완독 상태로 변경해주세요."),
    REVIEW_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "BOOK-RECORD-03", "리뷰를 입력할 수 없습니다."),
    FINISHED_DATE_REQUIRED(HttpStatus.BAD_REQUEST, "BOOK-RECORD-04", "완독 날짜를 입력해주세요."),
    INVALID_RATING(HttpStatus.BAD_REQUEST, "BOOK-RECORD-05", "별점은 0~5 사이의 0.5 단위로 입력 가능합니다."),
    NO_PERMISSION(HttpStatus.FORBIDDEN, "BOOK-RECORD-06", "자신이 작성한 독서 기록만 삭제할 수 있습니다."),
    QUOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "BOOK-RECORD-01", "해당 글귀를 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
