package com.bookee.bookee.domain.bookRecord.controller;

import com.bookee.bookee.domain.bookRecord.dto.request.BookRecordCreateRequest;
import com.bookee.bookee.domain.bookRecord.dto.request.BookRecordUpdateRequest;
import com.bookee.bookee.domain.bookRecord.dto.response.BookRecordDetailResponse;
import com.bookee.bookee.domain.bookRecord.dto.response.BookRecordListResponse;
import com.bookee.bookee.domain.bookRecord.service.BookRecordService;
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
        name = "독서 기록 API",
        description = "독서 기록 CRUD 관련 API"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book-records")
public class BookRecordController {

    private final ApiCommonResponseService apiCommonResponseService;
    private final BookRecordService bookRecordService;


    /**
     * 독서 기록 목록 조회
     */
    @Operation(
            summary = "독서 기록 목록 조회",
            description = "사용자의 독서 기록 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),

            @ApiResponse(
                    responseCode = "404",
                    description = "AUTH-04",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                  "success": false,
                  "code": "AUTH-04",
                  "message": "유저가 존재하지 않습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(responseCode = "500", description = "내부서버 오류"),
    })
    @GetMapping
    public ApiCommonResponse<List<BookRecordListResponse>> list(
            @Parameter(description = "사용자 ID", example = "1")
            @RequestParam Long userId
    ) {
        List<BookRecordListResponse> response = bookRecordService.list(userId);

        return apiCommonResponseService.success(response);
    }

    /**
     * 독서 기록 상세 조회
     */
    @Operation(
            summary = "독서 기록 상세 조회",
            description = "사용자의 독서 기록의 상세 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),

            @ApiResponse(
                    responseCode = "404",
                    description = "BOOK-RECORD-01",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                  "success": false,
                  "code": "BOOK-RECORD-01",
                  "message": "해당 독서록 기록을 찾을 수 없습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(responseCode = "500", description = "내부서버 오류"),
    })
    @GetMapping("/{bookRecordId}")
    public ApiCommonResponse<BookRecordDetailResponse> detail(
            @Parameter(description = "독서 기록 ID", example = "1")
            @PathVariable Long bookRecordId
    ) {
        BookRecordDetailResponse response = bookRecordService.detail(bookRecordId);

        return apiCommonResponseService.success(response);
    }

    /**
     * 독서 기록 생성
     */
    @Operation(
            summary = "독서 기록 생성",
            description = "새로운 독서 기록을 생성합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "생성 성공"),

            @ApiResponse(
                    responseCode = "400",
                    description = "입력값 오류",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "BOOK-RECORD-02",
                                            value = """
                    {
                      "success": false,
                      "code": "BOOK-RECORD-02",
                      "message": "완독 날짜를 입력할 수 없습니다.",
                      "data": null
                    }
                    """
                                    ),
                                    @ExampleObject(
                                            name = "BOOK-RECORD-03",
                                            value = """
                    {
                      "success": false,
                      "code": "BOOK-RECORD-03",
                      "message": "리뷰를 입력할 수 없습니다.",
                      "data": null
                    }
                    """
                                    ),
                                    @ExampleObject(
                                            name = "BOOK-RECORD-04",
                                            value = """
                    {
                      "success": false,
                      "code": "BOOK-RECORD-04",
                      "message": "완독 날짜를 입력해주세요.",
                      "data": null
                    }
                    """
                                    ),
                                    @ExampleObject(
                                            name = "BOOK-RECORD-05",
                                            value = """
                    {
                      "success": false,
                      "code": "BOOK-RECORD-05",
                      "message": "별점은 0~5 사이의 0.5 단위로 입력 가능합니다.",
                      "data": null
                    }
                    """
                                    )
                            }
                    )
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "유저 또는 책 없음",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "AUTH-04",
                                            value = """
                    {
                      "success": false,
                      "code": "AUTH-04",
                      "message": "유저가 존재하지 않습니다.",
                      "data": null
                    }
                    """
                                    ),
                                    @ExampleObject(
                                            name = "BOOK-01",
                                            value = """
                    {
                      "success": false,
                      "code": "BOOK-01",
                      "message": "해당 독서를 찾을 수 없습니다.",
                      "data": null
                    }
                    """
                                    )
                            }
                    )
            ),

            @ApiResponse(responseCode = "500", description = "내부서버 오류"),
    })
    @PostMapping
    public ApiCommonResponse<Long> create(@RequestBody BookRecordCreateRequest request) {
        Long id = bookRecordService.create(request);

        return apiCommonResponseService.success(id);
    }

    /**
     * 독서 기록 수정
     */
    @Operation(
            summary = "독서 기록 수정",
            description = "기존 독서 기록을 수정합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),

            @ApiResponse(
                    responseCode = "400",
                    description = "입력값 오류",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "BOOK-RECORD-02",
                                            value = """
                    {
                      "code": "BOOK-RECORD-02",
                      "message": "완독 날짜를 입력할 수 없습니다."
                    }
                    """
                                    ),
                                    @ExampleObject(
                                            name = "BOOK-RECORD-05",
                                            value = """
                    {
                      "code": "BOOK-RECORD-05",
                      "message": "별점은 0~5 사이의 0.5 단위로 입력 가능합니다."
                    }
                    """
                                    )
                            }
                    )
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "BOOK-RECORD-01",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                  "success": false,
                  "code": "BOOK-RECORD-01",
                  "message": "해당 독서록 기록을 찾을 수 없습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(responseCode = "500", description = "내부서버 오류"),
    })
    @PatchMapping
    public ApiCommonResponse<Long> update(@RequestBody BookRecordUpdateRequest request) {
        Long id = bookRecordService.update(request);

        return apiCommonResponseService.success(id);
    }

    /**
     * 독서 기록 삭제
     */
    @Operation(
            summary = "독서 기록 삭제",
            description = "기존 독서 기록을 삭제합니다."
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
                  "message": "자신이 작성한 독서 기록만 삭제할 수 있습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "BOOK-RECORD-01",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                  "success": false,
                  "code": "BOOK-RECORD-01",
                  "message": "해당 독서록 기록을 찾을 수 없습니다.",
                  "data": null
                }
                """
                            )
                    )
            ),

            @ApiResponse(responseCode = "500", description = "내부서버 오류"),
    })
    @DeleteMapping("/{bookRecordId}")
    public ApiCommonResponse<Void> delete(
            @Parameter(description = "사용자 ID", example = "1")
            @RequestParam Long userId,

            @Parameter(description = "독서 기록 ID", example = "1")
            @PathVariable Long bookRecordId
    ) {
        bookRecordService.delete(userId, bookRecordId);

        return apiCommonResponseService.success(null);
    }
}
