package com.dearme.backend.dearmebe.domain.memo.service;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCreateRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCreateResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoDetailResponse;
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

        return MemoListResponse.from(clientId, memos);
    }  // 듀기 걍 이대로 가묜 되는데여 ㅇㅅㅇ 다시 해볼개 404오류떠요,,,, 무슨 404 오류죠듀기 카톡으로 보내바요

    public MemoDetailResponse getMemoDetail(String clientId, Long memoId) {
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMO_NOT_FOUND, "해당 메모를 찾을 수 없습니다."));

        if (!memo.isOwner(clientId)) {
            throw new CustomException(ErrorCode.MEMO_ACCESS_DENIED, "해당 메모에 접근할 권한이 없습니다.");
        }

        return MemoDetailResponse.from(memo);
    }
}
