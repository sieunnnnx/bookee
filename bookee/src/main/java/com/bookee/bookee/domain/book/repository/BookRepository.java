package com.bookee.bookee.domain.book.repository;

import com.bookee.bookee.domain.book.entity.Book;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @NullMarked
    Optional<Book> findByIdAndIsDeletedFalse(Long bookId);
}
