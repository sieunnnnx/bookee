package com.bookee.bookee.domain.bookRecord.entity;

import com.bookee.bookee.domain.book.entity.Book;
import com.bookee.bookee.domain.user.entity.User;
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

    private String reviewTitle;

    private String reviewContent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;



    protected BookRecord(User user, Book book, ReadStatus readStatus, LocalDate startedAt,
                         LocalDate finishedAt, Double rating, String reviewTitle, String reviewContent) {

        this.user = user;
        this.book = book;
        this.readStatus = readStatus;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }


    /**
     * 독서 기록 생성
     */
    public static BookRecord create(User user, Book book, ReadStatus status,
                                    LocalDate startedAt, LocalDate finishedAt,
                                    Double rating, String reviewTitle, String reviewContent) {

        return new BookRecord(user, book, status, startedAt, finishedAt, rating, reviewTitle, reviewContent);
    }
}
