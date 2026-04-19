package com.bookee.bookee.domain.bookRecord.dto.request;

import com.bookee.bookee.domain.book.dto.BookResponse;
import com.bookee.bookee.domain.bookRecord.entity.ReadStatus;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class BookRecordCreateRequest {

    private Long userId;

    private Long bookId;

    private ReadStatus readStatus;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    private Double rating;

    private String reviewTitle;

    private String reviewContent;

    private BookResponse bookResponse;
}
