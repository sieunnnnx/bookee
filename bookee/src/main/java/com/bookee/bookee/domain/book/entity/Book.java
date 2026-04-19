package com.bookee.bookee.domain.book.entity;

import com.bookee.bookee.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Book extends BaseEntity {

    /**
     * ISBN (고유값)
     */
    @Column(nullable = false, unique = true)
    private String isbn;

    private String title;

    private String author;

    private String publisher;

    private String coverImgUrl;

    @Column(length = 1000)
    private String description;

    protected Book(String isbn, String title, String author,
                   String publisher, String coverImgUrl, String description) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.coverImgUrl = coverImgUrl;
        this.description = description;
    }

    /**
     * 책 생성 (중복 방지는 Service에서 처리)
     */
    public static Book create(String isbn, String title, String author,
                              String publisher, String coverImgUrl, String description) {

        return new Book(isbn, title, author, publisher, coverImgUrl, description);
    }
}
