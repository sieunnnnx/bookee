package com.bookee.bookee.domain.bookRecord.service;

import com.bookee.bookee.domain.bookRecord.dto.request.QuoteCreateRequest;
import com.bookee.bookee.domain.bookRecord.dto.request.QuoteUpdateRequest;
import com.bookee.bookee.domain.bookRecord.dto.response.QuoteResponse;
import com.bookee.bookee.domain.bookRecord.entity.BookRecord;
import com.bookee.bookee.domain.bookRecord.entity.Quote;
import com.bookee.bookee.domain.bookRecord.exception.BookRecordException;
import com.bookee.bookee.domain.bookRecord.repository.BookRecordRepository;
import com.bookee.bookee.domain.bookRecord.repository.QuoteRepository;
import com.bookee.bookee.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class QuoteService {

    private final BookRecordRepository bookRecordRepository;
    private final QuoteRepository quoteRepository;


    public List<QuoteResponse> list(Long bookRecordId) {

        return quoteRepository.findByBookRecord_idAndIsDeletedFalse(bookRecordId)
                .stream()
                .map(QuoteResponse::from)
                .toList();
    }

    public Long create(QuoteCreateRequest request) {
        BookRecord bookRecord = bookRecordRepository.findByIdAndIsDeletedFalse(request.getBookRecordId())
                .orElseThrow(() -> new CustomException(BookRecordException.BOOK_RECORD_NOT_FOUND));

        Quote quote = quoteRepository.save(
                Quote.create(
                        request.getContent(),
                        request.getPage(),
                        bookRecord
                )
        );

        return quote.getId();
    }

    public QuoteResponse detail(Long quoteId) {
        Quote quote = quoteRepository.findByIdAndIsDeletedFalse(quoteId)
                .orElseThrow(() -> new CustomException(BookRecordException.QUOTE_NOT_FOUND));

        return QuoteResponse.from(quote);
    }

    public Long update(QuoteUpdateRequest request) {
        Quote quote = quoteRepository.findByIdAndIsDeletedFalse(request.getQuoteId())
                .orElseThrow(() -> new CustomException(BookRecordException.QUOTE_NOT_FOUND));

        quote.update(
                request.getContent(),
                request.getPage()
        );

        return quote.getId();
    }

    public void delete(Long userId, Long quoteId) {

        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new CustomException(BookRecordException.QUOTE_NOT_FOUND));

        if (!quote.getBookRecord().getUser().getId().equals(userId)) {
            throw new CustomException(BookRecordException.NO_PERMISSION);
        }

        quote.delete();
    }
}
