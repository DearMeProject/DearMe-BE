package com.dearme.backend.dearmebe.domain.memo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record MemoCounselRequest(
        @NotEmpty(message = "선택한 메모 ID 배열은 비어 있을 수 없습니다.")
        @Size(min = 1, max = 3, message = "메모는 최소 1개, 최대 3개까지 선택할 수 있습니다.")
        List<Long> memoIds
) {
}