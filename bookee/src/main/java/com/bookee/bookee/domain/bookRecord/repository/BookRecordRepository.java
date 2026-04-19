package com.bookee.bookee.domain.bookRecord.repository;


import com.bookee.bookee.domain.bookRecord.entity.BookRecord;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {

    @NullMarked
    Optional<BookRecord> findByIdAndIsDeletedFalse(Long bookRecordId);

    List<BookRecord> findByUser_id(Long userId);
}