package com.bookee.bookee.domain.bookRecord.entity;

import com.bookee.bookee.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Quote extends BaseEntity {

    private String content;

    private Integer page;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_record_id")
    private BookRecord bookRecord;


    protected Quote(String content, Integer page, BookRecord bookRecord) {
        this.content = content;
        this.page = page;
        this.bookRecord = bookRecord;
    }

    /**
     * 글귀 생성
     */
    public static Quote create(String content, Integer page, BookRecord bookRecord) {
        return new Quote(content, page, bookRecord);
    }
}
