package com.bookee.bookee.domain.bookRecord.dto.response;


import com.bookee.bookee.domain.bookRecord.entity.Quote;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class QuoteResponse {

    private Long quoteId;

    private String content;

    private Long page;

    private LocalDate createdAt;


    public static QuoteResponse from(Quote quote) {

        return QuoteResponse.builder()
                .quoteId(quote.getId())
                .content(quote.getContent())
                .page(quote.getPage())
                .createdAt(quote.getCreatedAt().toLocalDate())
                .build();
    }
}
