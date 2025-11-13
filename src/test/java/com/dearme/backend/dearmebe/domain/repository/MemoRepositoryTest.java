package com.dearme.backend.dearmebe.domain.repository;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import com.dearme.backend.dearmebe.domain.memo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemoRepositoryTest {

    private static final String CLIENT_ID = "client123";
    private static final LocalDate DATE = LocalDate.of(2025, 11, 12);

    @Autowired
    private MemoRepository memoRepository;

    private Memo createMemo(String title, EmotionEmoji emoji, int score, LocalDate date) {
        return Memo.createMemo(CLIENT_ID, date, emoji, score, title, "내용입니다.");
    }

    @Test
    void 메모를_저장하고_조회할_수_있다() {

        Memo memo = createMemo("기분 좋은 하루", EmotionEmoji.HAPPY, 80, DATE);
        memoRepository.save(memo);

        List<Memo> found = memoRepository.findByClientId(CLIENT_ID);

        assertThat(found).hasSize(1)
                .extracting(Memo::getTitle)
                .containsExactly("기분 좋은 하루");
    }

    @Test
    void 같은_날짜에_여러_메모를_저장할_수_있다() {

        Memo memo1 = createMemo("제목1", EmotionEmoji.HAPPY, 80, DATE);
        Memo memo2 = createMemo("제목2", EmotionEmoji.SAD, 20, DATE);
        memoRepository.saveAll(List.of(memo1, memo2));

        List<Memo> all = memoRepository.findAllByClientIdOrderByDateAsc(CLIENT_ID);

        assertThat(all).hasSize(2);
        assertThat(all).extracting(Memo::getDate).containsOnly(DATE);
    }

    @Test
    void 특정_사용자의_전체_메모리스트를_오름차순으로_조회한다() {

        Memo earlier = createMemo("산책", EmotionEmoji.HAPPY, 20, LocalDate.of(2025, 11, 10));
        Memo later = createMemo("우울한 하루", EmotionEmoji.SAD, 60, LocalDate.of(2025, 11, 12));
        memoRepository.saveAll(List.of(earlier, later));

        List<Memo> memos = memoRepository.findAllByClientIdOrderByDateAsc(CLIENT_ID);

        assertThat(memos).hasSize(2);
        assertThat(memos.getFirst().getTitle()).isEqualTo("산책");
        assertThat(memos.get(0).getDate()).isBefore(memos.get(1).getDate());
    }
}
