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

    private Long page;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_record_id")
    private BookRecord bookRecord;


    protected Quote(String content, Long page, BookRecord bookRecord) {

        this.content = content;
        this.page = page;
        this.bookRecord = bookRecord;
    }


    /**
     * 글귀 생성
     */
    public static Quote create(String content, Long page, BookRecord bookRecord) {

        return new Quote(content, page, bookRecord);
    }

    /**
     * 글귀 수정
     */
    public void update(String content, Long page) {
        this.content = content;
        this.page = page;
    }
}
