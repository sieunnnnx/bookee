package com.bookee.bookee.domain.bookRecord.entity;

import com.bookee.bookee.domain.book.entity.Book;
import com.bookee.bookee.domain.bookRecord.exception.BookRecordException;
import com.bookee.bookee.domain.user.entity.User;
import com.bookee.bookee.global.entity.BaseEntity;
import com.bookee.bookee.global.exception.CustomException;
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
    public static BookRecord create(User user, Book book, ReadStatus readStatus,
                                    LocalDate startedAt, LocalDate finishedAt,
                                    Double rating, String reviewTitle, String reviewContent) {

        validate(readStatus, finishedAt, reviewTitle, reviewContent);
        validateRating(rating);

        return new BookRecord(user, book, readStatus, startedAt, finishedAt, rating, reviewTitle, reviewContent);
    }

    /**
     * 독서 기록 수정
     */
    public void update(ReadStatus readStatus, LocalDate startedAt, LocalDate finishedAt, Double rating, String reviewTitle, String reviewContent) {

        validate(readStatus, finishedAt, reviewTitle, reviewContent);
        validateRating(rating);

        this.readStatus = readStatus;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }


    /**
     * validation
     */
    private static void validate(ReadStatus status, LocalDate finishedAt, String reviewTitle, String reviewContent) {

        if (status != ReadStatus.COMPLETED) {
            if (finishedAt != null)
                throw new CustomException(BookRecordException.INVALID_FINISHED_DATE);

            if (reviewTitle != null && !reviewTitle.isBlank())
                throw new CustomException(BookRecordException.REVIEW_NOT_ALLOWED);

            if (reviewContent != null && !reviewContent.isBlank())
                throw new CustomException(BookRecordException.REVIEW_NOT_ALLOWED);
        }

        if (status == ReadStatus.COMPLETED && finishedAt == null)
            throw new CustomException(BookRecordException.FINISHED_DATE_REQUIRED);
    }

    private static void validateRating(Double rating) {

        if (rating == null) return;

        if (rating < 0 || rating > 5 || rating * 2 % 1 != 0) {
            throw new CustomException(BookRecordException.INVALID_RATING);
        }
    }
}
