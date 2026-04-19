package com.bookee.bookee.domain.bookRecord.repository;


import com.bookee.bookee.domain.bookRecord.entity.Quote;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @NullMarked
    Optional<Quote> findByIdAndIsDeletedFalse(Long quoteId);

    List<Quote> findByBookRecord_idAndIsDeletedFalse(Long bookRecordId);
}
