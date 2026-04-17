package com.bookee.bookee.domain.bookRecord.entity;

import com.bookee.bookee.domain.book.entity.Book;
import com.bookee.bookee.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class BookRecord extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    private Double rating = 0.0;

    private String review;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


    protected BookRecord(Book book, ReadStatus readStatus, LocalDate startedAt,
                         LocalDate finishedAt, Double rating, String review) {
        this.book = book;
        this.readStatus = readStatus;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.rating = rating;
        this.review = review;
    }

    /**
     * 독서 기록 생성
     */
    public static BookRecord create(Book book, ReadStatus status,
                                    LocalDate startedAt, LocalDate finishedAt,
                                    Double rating, String review) {
        return new BookRecord(book, status, startedAt, finishedAt, rating, review);
    }
}
