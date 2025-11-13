package com.dearme.backend.dearmebe.domain.service;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.domain.memo.dto.request.MemoCreateRequest;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoCreateResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoDetailResponse;
import com.dearme.backend.dearmebe.domain.memo.dto.response.MemoListResponse;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import com.dearme.backend.dearmebe.domain.memo.repository.MemoRepository;
import com.dearme.backend.dearmebe.domain.memo.service.MemoService;
import com.dearme.backend.dearmebe.global.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
                20,
                "기분 좋은 하루",
                "날씨가 좋아서 산책했다."
        );

        Memo memo = Memo.createMemo(
                1L,
                clientId,
                LocalDate.parse("2025-11-12"),
                EmotionEmoji.HAPPY,
                20,
                "기분 좋은 하루",
                "날씨가 좋아서 산책했다."
        );

        given(memoRepository.save(any(Memo.class))).willReturn(memo);

        MemoCreateResponse response = memoService.createMemo(clientId, request);

        assertThat(response.getMemoId()).isEqualTo(1L);
    }

    @Test
    void 전체_메모리스트를_정상적으로_조회할_수_있다() {

        String clientId = "client123";

        Memo memo1 = Memo.createMemo(
                clientId,
                LocalDate.of(2025, 11, 12),
                EmotionEmoji.HAPPY,
                80,
                "즐거운 날",
                "케이크 맛집을 찾았다"
        );

        Memo memo2 = Memo.createMemo(
                clientId,
                LocalDate.of(2025, 11, 10),
                EmotionEmoji.SAD,
                80,
                "우울한 하루",
                "비가 왔다"
        );

        given(memoRepository.findAllByClientIdOrderByDateAsc(clientId))
                .willReturn(List.of(memo1, memo2));

        MemoListResponse response = memoService.getAllMemos(clientId);

        assertThat(response).isNotNull();
        assertThat(response.getClientId()).isEqualTo(clientId);
        assertThat(response.getMemos()).hasSize(2);
        assertThat(response.getMemos().get(0).getTitle()).isEqualTo("즐거운 날");
    }

    @Test
    void 메모를_ID로_정상_조회할_수_있다() {

        String clientId = "client123";
        Memo memo = Memo.createMemo(clientId, LocalDate.now(), EmotionEmoji.HAPPY, 20, "제목", "내용");
        given(memoRepository.findById(any(Long.class))).willReturn(Optional.of(memo));

        MemoDetailResponse response = memoService.getMemoDetail(clientId, 1L);

        assertThat(response.getContent()).isEqualTo("내용");
    }

    @Test
    void 본인_메모가_아닐_경우_예외가_발생한다() {

        Memo memo = Memo.createMemo("otherUser", LocalDate.now(), EmotionEmoji.HAPPY, 20, "제목", "내용");
        given(memoRepository.findById(any(Long.class))).willReturn(Optional.of(memo));

        assertThatThrownBy(() -> memoService.getMemoDetail("client123", 1L))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("해당 메모에 접근할 권한이 없습니다.");
    }
}
