package com.bookee.bookee.domain.bookRecord.dto.request;


import lombok.Getter;

@Getter
public class QuoteUpdateRequest {

    private Long quoteId;

    private String content;

    private Long page;
}
