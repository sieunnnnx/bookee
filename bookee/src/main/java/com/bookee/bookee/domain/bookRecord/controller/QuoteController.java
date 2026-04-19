package com.bookee.bookee.domain.bookRecord.controller;


import com.bookee.bookee.domain.bookRecord.dto.request.QuoteCreateRequest;
import com.bookee.bookee.domain.bookRecord.dto.request.QuoteUpdateRequest;
import com.bookee.bookee.domain.bookRecord.dto.response.QuoteResponse;
import com.bookee.bookee.domain.bookRecord.service.QuoteService;
import com.bookee.bookee.global.api.ApiCommonResponse;
import com.bookee.bookee.global.api.ApiCommonResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final ApiCommonResponseService apiCommonResponseService;


    /**
     * 글귀 목록 조회
     */
    @Operation(
            summary = "글귀 목록 조회",
            description = "특정 독서 기록의 글귀 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "내부서버 오류")
    })
            @GetMapping
    public ApiCommonResponse<List<QuoteResponse>> list(
            @Parameter(description = "독서 기록 ID", example = "1")
            @RequestParam Long bookRecordId) {

        return apiCommonResponseService.success(quoteService.list(bookRecordId));
    }

    /**
     * 글귀 생성
     */
    @Operation(
            summary = "글귀 생성",
            description = "새로운 글귀를 생성합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "내부서버 오류")
    })
    @PostMapping
    public ApiCommonResponse<Long> create(@RequestBody QuoteCreateRequest request) {

        return apiCommonResponseService.success(quoteService.create(request));
    }

    /**
     * 글귀 수정
     */
    @Operation(
            summary = "글귀 수정",
            description = "기존 글귀를 수정합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),

            @ApiResponse(
                    responseCode = "404",
                    description = "BOOK-RECORD-07",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                  "success": false,
                  "code": "BOOK-RECORD-07",
                  "message": "해당 글귀를 찾을 수 없습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(responseCode = "500", description = "내부서버 오류"),
    })
    @PatchMapping
    public ApiCommonResponse<Long> update(@RequestBody QuoteUpdateRequest request) {

        return apiCommonResponseService.success(quoteService.update(request));
    }

    /**
     * 글귀 삭제 (Soft Delete)
     */
    @Operation(
            summary = "글귀 삭제",
            description = "기존 글귀를 삭제합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),

            @ApiResponse(
                    responseCode = "403",
                    description = "BOOK-RECORD-06",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                  "success": false,
                  "code": "BOOK-RECORD-06",
                  "message": "삭제 권한이 없습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "BOOK-RECORD-07",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                  "success": false,
                  "code": "BOOK-RECORD-07",
                  "message": "해당 글귀를 찾을 수 없습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(responseCode = "500", description = "내부서버 오류"),
    })
    @DeleteMapping("/{quoteId}")
    public ApiCommonResponse<Void> delete(
            @Parameter(description = "사용자 ID", example = "1")
            @RequestParam Long userId,

            @Parameter(description = "글귀 ID", example = "1")
            @PathVariable Long quoteId) {

        quoteService.delete(userId, quoteId);

        return apiCommonResponseService.success(null);
    }
}
