package com.dearme.backend.dearmebe.domain.memo.controller;

import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCounselRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCreateRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCounselResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCreateResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoDetailResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoListResponse;
import com.dearme.backend.dearmebe.domain.memo.service.MemoAiService;
import com.dearme.backend.dearmebe.domain.memo.service.MemoService;
import com.dearme.backend.dearmebe.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;
    private final MemoAiService memoAiService;

    public MemoController(MemoService memoService,  MemoAiService memoAiService) {
        this.memoService = memoService;
        this.memoAiService = memoAiService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MemoCreateResponse>> createMemo(
            @RequestHeader("X-Client-Id") String ClientId,
            @RequestBody @Valid MemoCreateRequest request
    ) {
        MemoCreateResponse memoResponse = memoService.createMemo(ClientId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("메모 작성 완료", memoResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MemoListResponse>> getAllMemos(
            @RequestHeader("X-Client-Id") String clientId
    ) {
        MemoListResponse response = memoService.getAllMemos(clientId);
        return ResponseEntity
                .ok(ApiResponse.ok("조회 성공", response));
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<ApiResponse<MemoDetailResponse>> getMemoDetail(
            @RequestHeader("X-Client-Id") String clientId,
            @PathVariable Long memoId
    ) {
        MemoDetailResponse response = memoService.getMemoDetail(clientId, memoId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.ok("조회 성공", response));
    }

    @PostMapping("/counsel")
    public ResponseEntity<ApiResponse<MemoCounselResponse>> createCounsel(
            @RequestHeader("X-Client-Id") String clientId,
            @RequestBody @Valid MemoCounselRequest request
    ) {
        MemoCounselResponse response = memoAiService.createCounsel(clientId, request);
        return ResponseEntity
                .ok(ApiResponse.ok("조회 성공", response));
    }
}
