package com.bookee.bookee.domain.book.exception;

import com.bookee.bookee.global.api.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookException implements BaseCode {

    BOOK_NOT_FOUND("BOOK-01", "해당 독서를 찾을 수 없습니다.");

    private final String code;
    private final String message;
}
