package com.bookee.bookee.domain.bookRecord.controller;


import com.bookee.bookee.domain.bookRecord.dto.request.QuoteCreateRequest;
import com.bookee.bookee.domain.bookRecord.dto.request.QuoteUpdateRequest;
import com.bookee.bookee.domain.bookRecord.dto.response.QuoteResponse;
import com.bookee.bookee.domain.bookRecord.service.QuoteService;
import com.bookee.bookee.global.api.ApiResponse;
import com.bookee.bookee.global.api.ApiResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "독서 글귀 API",
        description = "독서 글귀(Quote) 관련 API"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;
    private final ApiResponseService apiResponseService;


    /**
     * 글귀 목록 조회
     */
    @Operation(
            summary = "글귀 목록 조회",
            description = "특정 독서 기록의 글귀 목록을 조회합니다."
    )
    @GetMapping
    public ApiResponse<List<QuoteResponse>> list(
            @Parameter(description = "독서 기록 ID", example = "1")
            @RequestParam Long bookRecordId) {

        return apiResponseService.success(quoteService.list(bookRecordId));
    }

    /**
     * 글귀 생성
     */
    @Operation(
            summary = "글귀 생성",
            description = "새로운 글귀를 생성합니다."
    )
    @PostMapping
    public ApiResponse<Long> create(@RequestBody QuoteCreateRequest request) {

        return apiResponseService.success(quoteService.create(request));
    }

    /**
     * 글귀 수정
     */
    @Operation(
            summary = "글귀 수정",
            description = "기존 글귀를 수정합니다."
    )
    @PatchMapping
    public ApiResponse<Long> update(@RequestBody QuoteUpdateRequest request) {

        return apiResponseService.success(quoteService.update(request));
    }

    /**
     * 글귀 삭제 (Soft Delete)
     */
    @Operation(
            summary = "글귀 삭제",
            description = "기존 글귀를 삭제합니다."
    )
    @DeleteMapping("/{quoteId}")
    public ApiResponse<Void> delete(
            @Parameter(description = "사용자 ID", example = "1")
            @RequestParam Long userId,

            @Parameter(description = "글귀 ID", example = "1")
            @PathVariable Long quoteId) {

        quoteService.delete(userId, quoteId);

        return apiResponseService.success(null);
    }
}
