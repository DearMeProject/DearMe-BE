package com.dearme.backend.dearmebe.domain.service;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCreateRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCreateResponse;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import com.dearme.backend.dearmebe.domain.memo.repository.MemoRepository;
import com.dearme.backend.dearmebe.domain.memo.service.MemoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MemoServiceTest {

    @Mock
    private MemoRepository memoRepository;

    @InjectMocks
    private MemoService memoService;

    @Test
    void 메모를_정상적으로_생성할_수_있다() {

        String clientId = "client123";
        MemoCreateRequest request = new MemoCreateRequest(
                "2025-11-12",
                "😀",
                85,
                "기분 좋은 하루",
                "날씨가 좋아서 산책했다."
        );

        Memo memo = Memo.createMemo(
                1L,
                clientId,
                LocalDate.parse("2025-11-12"),
                EmotionEmoji.HAPPY,
                80,
                "기분 좋은 하루",
                "날씨가 좋아서 산책했다."
        );

        given(memoRepository.save(any(Memo.class))).willReturn(memo);

        MemoCreateResponse response = memoService.createMemo(clientId, request);

        assertThat(response.getMemoId()).isEqualTo(1L);
    }
}
