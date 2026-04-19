package com.bookee.bookee.domain.bookRecord.service;

import com.bookee.bookee.domain.book.entity.Book;
import com.bookee.bookee.domain.book.exception.BookException;
import com.bookee.bookee.domain.book.repository.BookRepository;
import com.bookee.bookee.domain.bookRecord.dto.request.BookRecordCreateRequest;
import com.bookee.bookee.domain.bookRecord.dto.request.BookRecordUpdateRequest;
import com.bookee.bookee.domain.bookRecord.dto.response.BookRecordDetailResponse;
import com.bookee.bookee.domain.bookRecord.dto.response.BookRecordListResponse;
import com.bookee.bookee.domain.bookRecord.entity.BookRecord;
import com.bookee.bookee.domain.bookRecord.entity.Quote;
import com.bookee.bookee.domain.bookRecord.exception.BookRecordException;
import com.bookee.bookee.domain.bookRecord.repository.BookRecordRepository;
import com.bookee.bookee.domain.bookRecord.repository.QuoteRepository;
import com.bookee.bookee.domain.user.entity.User;
import com.bookee.bookee.domain.user.exception.UserException;
import com.bookee.bookee.domain.user.repository.UserRepository;
import com.bookee.bookee.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookRecordService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final QuoteRepository quoteRepository;
    private final BookRecordRepository bookRecordRepository;


    /**
     * 특정 사용자의 독서 기록 목록을 조회한다.
     *
     * @param userId 사용자 ID
     * @return 독서 기록 목록
     * @throws CustomException 사용자가 존재하지 않는 경우
     */
    public List<BookRecordListResponse> list(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserException.USER_NOT_FOUND));

        return bookRecordRepository
                .findByUser_id(user.getId())
                .stream()
                .map(BookRecordListResponse::from)
                .toList();
    }

    /**
     * 독서 기록 상세 정보를 조회한다.
     *
     * <p>
     * 해당 독서 기록에 연결된 글귀(Quote) 목록도 함께 조회하여 반환한다.
     * </p>
     *
     * @param bookRecordId 독서 기록 ID
     * @return 독서 기록 상세 정보
     * @throws CustomException 독서 기록이 존재하지 않는 경우
     */
    public BookRecordDetailResponse detail(Long bookRecordId) {
        BookRecord bookRecord = bookRecordRepository.findById(bookRecordId)
                .orElseThrow(() -> new CustomException(BookRecordException.BOOK_RECORD_NOT_FOUND));

        List<Quote> quotes = quoteRepository.findByBookRecord_id(bookRecordId);

        return BookRecordDetailResponse.from(bookRecord, quotes);
    }

    /**
     * 새로운 독서 기록을 생성한다.
     *
     * <p>
     * 사용자와 책 정보를 기반으로 독서 기록을 생성하며,
     * 상태, 날짜, 별점, 리뷰 등의 정보를 포함한다.
     * </p>
     *
     * @param request 독서 기록 생성 요청 DTO
     * @return 생성된 독서 기록 ID
     * @throws CustomException 사용자가 존재하지 않거나 책이 존재하지 않는 경우
     */
    public Long create(BookRecordCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(UserException.USER_NOT_FOUND));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new CustomException(BookException.BOOK_NOT_FOUND));

        BookRecord bookRecord = bookRecordRepository.save(
                BookRecord.create(
                        user,
                        book,
                        request.getReadStatus(),
                        request.getStartedAt(),
                        request.getFinishedAt(),
                        request.getRating(),
                        request.getReviewTitle(),
                        request.getReviewContent()
                        ));

        return bookRecord.getId();
    }

    /**
     * 기존 독서 기록을 수정한다.
     *
     * @param request 독서 기록 수정 요청 DTO
     * @return 수정된 독서 기록 ID
     * @throws CustomException 독서 기록이 존재하지 않는 경우
     */
    public Long update(BookRecordUpdateRequest request) {
        BookRecord bookRecord = bookRecordRepository.findById(request.getBookRecordId())
                .orElseThrow(() -> new CustomException(BookRecordException.BOOK_RECORD_NOT_FOUND));

        bookRecord.update(
                request.getReadStatus(),
                request.getStartedAt(),
                request.getFinishedAt(),
                request.getRating(),
                request.getReviewTitle(),
                request.getReviewContent()
        );

        return bookRecord.getId();
    }

    /**
     * 독서 기록을 삭제한다. (Soft Delete)
     *
     * <p>
     * 실제 DB에서 삭제하지 않고 isDeleted 값을 true로 변경한다.
     * </p>
     *
     * <p>
     * 삭제 권한:
     * - 해당 독서 기록의 소유자만 삭제 가능
     * </p>
     *
     * @param userId 사용자 ID
     * @param bookRecordId 독서 기록 ID
     * @throws CustomException 독서 기록이 존재하지 않거나 삭제 권한이 없는 경우
     */
    public void delete(Long userId, Long bookRecordId) {

        BookRecord bookRecord = bookRecordRepository.findById(bookRecordId)
                .orElseThrow(() -> new CustomException(BookRecordException.BOOK_RECORD_NOT_FOUND));

        if (!bookRecord.getUser().getId().equals(userId))
            throw new CustomException(BookRecordException.NO_PERMISSION);

        bookRecord.delete();
    }
}
