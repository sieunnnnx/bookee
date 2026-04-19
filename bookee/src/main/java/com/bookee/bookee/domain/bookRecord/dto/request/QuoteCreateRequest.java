package com.bookee.bookee.domain.bookRecord.dto.request;


import lombok.Getter;

@Getter
public class QuoteCreateRequest {

    private Long bookRecordId;

    private String content;

    private Long page;
}
