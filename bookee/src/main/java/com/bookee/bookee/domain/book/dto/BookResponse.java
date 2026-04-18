package com.bookee.bookee.domain.book.dto;

import com.bookee.bookee.domain.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponse {

    private Long bookId;

    private String isbn;

    private String title;

    private String author;

    private String publisher;

    private String coverImgUrl;

    private String description;


    public static BookResponse from(Book book) {

        return BookResponse.builder()
                .bookId(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .coverImgUrl(book.getCoverImgUrl())
                .description(book.getDescription())
                .build();
    }
}
