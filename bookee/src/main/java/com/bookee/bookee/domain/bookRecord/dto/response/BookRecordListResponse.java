package com.bookee.bookee.domain.bookRecord.dto.response;

import com.bookee.bookee.domain.book.dto.BookResponse;
import com.bookee.bookee.domain.book.entity.Book;
import com.bookee.bookee.domain.bookRecord.entity.BookRecord;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BookRecordListResponse {

    private BookResponse bookResponse;

    private Long bookRecordId;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    private Double rating;

    private String reviewTitle;

    private String reviewContent;


    public static BookRecordListResponse from(BookRecord bookRecord) {

        return BookRecordListResponse.builder()
                .bookResponse(BookResponse.from(bookRecord.getBook()))
                .bookRecordId(bookRecord.getId())
                .startedAt(bookRecord.getStartedAt())
                .finishedAt(bookRecord.getFinishedAt())
                .rating(bookRecord.getRating())
                .reviewTitle(bookRecord.getReviewTitle())
                .reviewContent(bookRecord.getReviewContent())
                .build();
    }
}