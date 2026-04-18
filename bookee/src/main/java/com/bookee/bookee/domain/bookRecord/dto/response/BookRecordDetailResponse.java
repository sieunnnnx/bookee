package com.bookee.bookee.domain.bookRecord.dto.response;

import com.bookee.bookee.domain.bookRecord.entity.BookRecord;
import com.bookee.bookee.domain.bookRecord.entity.Quote;
import com.bookee.bookee.domain.bookRecord.entity.ReadStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class BookRecordDetailResponse {

    private Long bookRecordId;

    private ReadStatus readStatus;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    private Double rating;

    private String reviewTitle;

    private String reviewContent;

    private List<QuoteResponse> quoteResponses;


    public static BookRecordDetailResponse from(BookRecord bookRecord, List<Quote> quotes) {

        return BookRecordDetailResponse.builder()
                .bookRecordId(bookRecord.getId())
                .readStatus(bookRecord.getReadStatus())
                .startedAt(bookRecord.getStartedAt())
                .finishedAt(bookRecord.getFinishedAt())
                .rating(bookRecord.getRating())
                .reviewTitle(bookRecord.getReviewTitle())
                .reviewContent(bookRecord.getReviewContent())
                .quoteResponses(
                        quotes.stream()
                                .map(QuoteResponse::from)
                                .toList()
                )
                .build();
    }
}
