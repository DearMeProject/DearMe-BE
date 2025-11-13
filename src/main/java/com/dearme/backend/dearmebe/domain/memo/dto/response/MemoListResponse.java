package com.dearme.backend.dearmebe.domain.memo.dto.response;

import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MemoListResponse {

    private String clientId;
    private List<MemoSimpleResponse> memos;

    public static MemoListResponse from(String clientId, List<Memo> memos) {
        List<MemoSimpleResponse> memoList = memos.stream()
                .map(MemoSimpleResponse::from)
                .collect(Collectors.toList());

        return new MemoListResponse(clientId, memoList);
    }

    @Getter
    @AllArgsConstructor
    public static class MemoSimpleResponse {

        private Long memoId;
        private String date;
        private String emoji;
        private String title;
        private int emotionScore;

        public static MemoSimpleResponse from(Memo memo) {
            return new MemoSimpleResponse(
                    memo.getId(),
                    memo.getDate().toString(),
                    memo.getEmoji().toString(),
                    memo.getTitle(),
                    memo.getEmotionScore()
            );
        }
    }
}
