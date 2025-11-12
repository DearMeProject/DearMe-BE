package com.dearme.backend.dearmebe.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 공통
    BAD_REQUEST(400, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND(404, HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
    FORBIDDEN(403, HttpStatus.FORBIDDEN, "요청에 대한 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),

    // 토큰 관련
    TOKEN_EXPIRED(2101, HttpStatus.UNAUTHORIZED, "Access Token이 만료되었습니다."),
    INVALID_TOKEN(2102, HttpStatus.UNAUTHORIZED, "유효하지 않은 Access Token입니다."),
    TOKEN_MISSING(2103, HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다."),
    TOKEN_SIGNATURE_INVALID(2104, HttpStatus.UNAUTHORIZED, "토큰 서명이 유효하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(2105, HttpStatus.UNAUTHORIZED, "유효하지 않은 Refresh Token입니다."),
    REFRESH_TOKEN_NOT_FOUND(2106, HttpStatus.NOT_FOUND, "Refresh Token을 찾을 수 없습니다."),
    REFRESH_TOKEN_MISMATCH(2107, HttpStatus.UNAUTHORIZED, "DB의 Refresh Token과 일치하지 않습니다."),

    // 메모 관련
    MEMO_NOT_FOUND(404, HttpStatus.NOT_FOUND, "해당 메모를 찾을 수 없습니다."),
    MEMO_ACCESS_DENIED(403, HttpStatus.FORBIDDEN, "해당 메모에 접근할 권한이 없습니다."),
    INVALID_MEMO_REQUEST(400, HttpStatus.BAD_REQUEST, "메모 요청 데이터가 올바르지 않습니다."),
    INVALID_DATE_FORMAT(400, HttpStatus.BAD_REQUEST, "날짜 형식은 YYYY-MM-DD 이어야 합니다."),
    INVALID_EMOJI_TYPE(400, HttpStatus.BAD_REQUEST, "유효하지 않는 이모지입니다."),

    // AI 상담 관련
    AI_COUNSEL_FAILED(200, HttpStatus.OK, "AI 상담 응답을 생성하지 못했습니다. 기본 문구로 대체합니다.");



    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Integer getCode() { return code; }
    public HttpStatus getHttpStatus() { return httpStatus; }
    public String getMessage() { return message; }
}

