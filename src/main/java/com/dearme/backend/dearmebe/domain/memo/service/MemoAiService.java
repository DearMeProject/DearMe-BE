package com.dearme.backend.dearmebe.domain.memo.service;

import com.dearme.backend.dearmebe.common.util.PromptBuilder;
import com.dearme.backend.dearmebe.common.util.StressScoreCalculator;
import com.dearme.backend.dearmebe.domain.external.ai.AiClient;
import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCounselRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCounselResponse;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import com.dearme.backend.dearmebe.domain.memo.repository.MemoRepository;
import com.dearme.backend.dearmebe.global.exception.CustomException;
import com.dearme.backend.dearmebe.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoAiService {

    private final MemoRepository memoRepository;
    private final PromptBuilder promptBuilder;
    private final AiClient aiClient;

    public MemoCounselResponse createCounsel(String clientId, MemoCounselRequest request) {

        List<Memo> memos = memoRepository.findAllById(request.memoIds());

        validateOwnerShip(clientId, memos);

        int stressScore = StressScoreCalculator.calculateAverage(memos);
        String tone = StressScoreCalculator.classifyTone(stressScore);
        String prompt = promptBuilder.buildPrompt("디미", stressScore, tone, memos);

        try {
            String aiMessage = aiClient.requestCounsel(prompt);
            String formatted = String.format("디미님, 오늘의 스트레스 지수는 %d점이에요. %s", stressScore, aiMessage);
            return new MemoCounselResponse(stressScore, formatted);
        } catch (Exception e) {
            String fallback = String.format("디미님, 오늘의 스트레스 지수는 %d점이에요. 오늘은 조금 힘든 하루였지만, 내일은 더 나을 거예요", stressScore);
            return new MemoCounselResponse(stressScore, fallback);
        }
    }

    private void validateOwnerShip(String clientId, List<Memo> memos) {
        if (memos.isEmpty()) {
            throw new CustomException(ErrorCode.MEMO_NOT_FOUND, "해당 메모를 찾을 수 없습니다.");
        }

        boolean hasOtherOwner = memos.stream()
                .anyMatch(memo -> !memo.isOwner(clientId));

        if (memos.stream().anyMatch(m -> !m.isOwner(clientId)))
            throw new CustomException(ErrorCode.MEMO_ACCESS_DENIED, "해당 메모에 접근할 권한이 없습니다.");
    }
}
