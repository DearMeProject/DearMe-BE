package com.dearme.backend.dearmebe.domain.memo.service;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCreateRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCreateResponse;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import com.dearme.backend.dearmebe.domain.memo.repository.MemoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoCreateResponse createMemo(String clientId, MemoCreateRequest request) {
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
}
