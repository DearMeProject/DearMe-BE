package com.dearme.backend.dearmebe.domain.memo.dto.response;

public record MemoCounselResponse(
        int stressScore,
        // String emotionStatus,  // “좋음 / 보통 / 기분나쁨”
        String counselResult
        // boolean isFallback     // OpenAI 장애 시 대체 문구인지 여부
) {}

