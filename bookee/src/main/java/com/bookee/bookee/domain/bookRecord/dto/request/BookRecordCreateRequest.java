package com.bookee.bookee.domain.bookRecord.dto.request;

import com.bookee.bookee.domain.book.dto.BookResponse;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class BookRecordCreateRequest {

    private Long userId;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    private Double rating;

    private String reviewTitle;

    private String reviewContent;

    private BookResponse bookResponse;
}
