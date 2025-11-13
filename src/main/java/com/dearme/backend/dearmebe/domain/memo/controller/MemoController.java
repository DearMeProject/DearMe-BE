package com.dearme.backend.dearmebe.domain.memo.controller;

import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCreateRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCreateResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoListResponse;
import com.dearme.backend.dearmebe.domain.memo.service.MemoService;
import com.dearme.backend.dearmebe.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/api/memos")
    public ResponseEntity<ApiResponse<MemoCreateResponse>>  createMemo(
            @RequestHeader("X-Client-Id") String ClientId,
            @RequestBody @Valid MemoCreateRequest request
    ){
        MemoCreateResponse memoResponse = memoService.createMemo(ClientId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("메모 작성 완료", memoResponse));
    }

    @GetMapping("/api/memos")
    public ResponseEntity<ApiResponse<MemoListResponse>> getAllMemos(
            @RequestHeader("X-Client-Id") String clientId
    ) {
        MemoListResponse response = memoService.getAllMemos(clientId);
        return ResponseEntity
                .ok(ApiResponse.ok("조회 성공", response));
    }
}
