package com.bookee.bookee.domain.bookRecord.controller;

import com.bookee.bookee.domain.bookRecord.dto.request.BookRecordCreateRequest;
import com.bookee.bookee.domain.bookRecord.dto.request.BookRecordUpdateRequest;
import com.bookee.bookee.domain.bookRecord.dto.response.BookRecordDetailResponse;
import com.bookee.bookee.domain.bookRecord.dto.response.BookRecordListResponse;
import com.bookee.bookee.domain.bookRecord.service.BookRecordService;
import com.bookee.bookee.global.api.ApiResponse;
import com.bookee.bookee.global.api.ApiResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "독서 기록 API",
        description = "독서 기록 CRUD 관련 API"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book-records")
public class BookRecordController {

    private final ApiResponseService apiResponseService;
    private final BookRecordService bookRecordService;


    /**
     * 독서 기록 목록 조회
     */
    @Operation(
            summary = "독서 기록 목록 조회",
            description = "사용자의 독서 기록 목록을 조회합니다."
    )
    @GetMapping
    public ApiResponse<List<BookRecordListResponse>> list(
            @Parameter(description = "사용자 ID", example = "1")
            @RequestParam Long userId
    ) {
        List<BookRecordListResponse> response = bookRecordService.list(userId);

        return apiResponseService.success(response);
    }

    /**
     * 독서 기록 상세 조회
     */
    @Operation(
            summary = "독서 기록 상세 조회",
            description = "사용자의 독서 기록의 상세 정보를 조회합니다."
    )
    @GetMapping("/{bookRecordId}")
    public ApiResponse<BookRecordDetailResponse> detail(
            @Parameter(description = "독서 기록 ID", example = "1")
            @PathVariable Long bookRecordId
    ) {
        BookRecordDetailResponse response = bookRecordService.detail(bookRecordId);

        return apiResponseService.success(response);
    }

    /**
     * 독서 기록 생성
     */
    @Operation(
            summary = "독서 기록 생성",
            description = "새로운 독서 기록을 생성합니다."
    )
    @PostMapping
    public ApiResponse<Long> create(@RequestBody BookRecordCreateRequest request) {
        Long id = bookRecordService.create(request);

        return apiResponseService.success(id);
    }

    /**
     * 독서 기록 수정
     */
    @Operation(
            summary = "독서 기록 수정",
            description = "기존 독서 기록을 수정합니다."
    )
    @PatchMapping
    public ApiResponse<Long> update(@RequestBody BookRecordUpdateRequest request) {
        Long id = bookRecordService.update(request);

        return apiResponseService.success(id);
    }

    /**
     * 독서 기록 삭제
     */
    @Operation(
            summary = "독서 기록 삭제",
            description = "기존 독서 기록을 삭제합니다."
    )
    @DeleteMapping("/{bookRecordId}")
    public ApiResponse<Void> delete(
            @Parameter(description = "사용자 ID", example = "1")
            @RequestParam Long userId,

            @Parameter(description = "독서 기록 ID", example = "1")
            @PathVariable Long bookRecordId
    ) {
        bookRecordService.delete(userId, bookRecordId);

        return apiResponseService.success(null);
    }
}
