package com.dearme.backend.dearmebe.domain.repository;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import com.dearme.backend.dearmebe.domain.memo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class MemoRepositoryTest {
    
    @Autowired
    private MemoRepository memoRepository;
    
    @Test
    void 메모를_저장하고_조회할_수_있다() {
        Memo memo = Memo.createMemo(
                "client123",
                LocalDate.of(2025, 11, 12),
                EmotionEmoji.HAPPY,
                80,
                "기분 좋은 하루",
                "오늘은 날씨가 좋아서 산책했다."
        );

        Memo saved = memoRepository.save(memo);
        List<Memo> found = memoRepository.findByClientId(saved.getClientId());

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getTitle()).isEqualTo("기분 좋은 하루");
    }

    @Test
    void 같은_날짜에_여러_메모를_저장할_수_있다() {
        var date = LocalDate.of(2025, 11, 12);
        var memo1 = Memo.createMemo("client123", date, EmotionEmoji.HAPPY, 80, "제목1", "내용1");
        var memo2 = Memo.createMemo("client123", date, EmotionEmoji.SAD,   20, "제목2", "내용2");

        memoRepository.save(memo1);
        memoRepository.save(memo2);
        var all = memoRepository.findAllByClientIdOrderByDateAsc("client123");

        assertThat(all).hasSize(2);
        assertThat(all).extracting(Memo::getDate).containsOnly(date);

    }

    @Test
    void 특정_사용자의_전체_메모리스트를_오름차순으로_조회한다() {

        String clientId = "client123";

        Memo memo1 = Memo.createMemo(clientId, LocalDate.of(2025, 11, 10),
                EmotionEmoji.HAPPY, 20, "산책", "산책을 해서 기분이 좋았다.");
        Memo memo2 = Memo.createMemo(clientId, LocalDate.of(2025, 11, 12),
                EmotionEmoji.SAD, 60, "우울한 하루", "비가 와서 우울했다.");

        memoRepository.save(memo1);
        memoRepository.save(memo2);

        List<Memo> memos = memoRepository.findAllByClientIdOrderByDateAsc(clientId);

        assertThat(memos).hasSize(2);
        assertThat(memos.get(0).getDate()).isBefore(memos.get(1).getDate());
        assertThat(memos.get(0).getTitle()).isEqualTo("산책");
    }
}
