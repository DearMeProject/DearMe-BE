package com.dearme.backend.dearmebe.domain.memo.service;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCreateRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCreateResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoListResponse;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import com.dearme.backend.dearmebe.domain.memo.repository.MemoRepository;
import com.dearme.backend.dearmebe.global.exception.CustomException;
import com.dearme.backend.dearmebe.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoCreateResponse createMemo(String clientId, MemoCreateRequest request) {
        EmotionEmoji emoji = EmotionEmoji.from(request.getEmoji());

        if (!emoji.isValidScore(request.getEmotionScore())) {
            throw new CustomException(ErrorCode.INVALID_EMOTION_SCORE, "유효하지 않은 감정 점수입니다.");
        }

        Memo memo = Memo.createMemo(
                clientId,
                LocalDate.parse(request.getDate()),
                EmotionEmoji.from(request.getEmoji()),
                request.getEmotionScore(),
                request.getTitle(),
                request.getContent()
        );

        Memo saved = memoRepository.save(memo);
        return MemoCreateResponse.from(saved);
    }

    public MemoListResponse getAllMemos(String clientId) {
        List<Memo> memos = memoRepository.findAllByClientIdOrderByDateAsc(clientId);

        if (memos.isEmpty()) {
            throw new CustomException(ErrorCode.NO_MEMOS_FOUND, "등록된 메모가 없습니다.");
        }

        return MemoListResponse.from(clientId, memos);
    }
}
