package com.dearme.backend.dearmebe.domain.memo.dto.response;

import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoDetailResponse {

    private String content;

    public static MemoDetailResponse from(Memo memo) {
        return new MemoDetailResponse(memo.getContent());
    }

}
