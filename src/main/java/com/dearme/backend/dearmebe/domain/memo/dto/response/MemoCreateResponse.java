package com.dearme.backend.dearmebe.domain.memo.dto.response;

import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoCreateResponse {
    private Long memoId;

    public static MemoCreateResponse from(Memo memo) {
        return new MemoCreateResponse(memo.getId());
    }
}
