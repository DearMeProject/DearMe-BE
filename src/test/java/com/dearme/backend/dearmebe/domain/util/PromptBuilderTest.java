package com.dearme.backend.dearmebe.domain.util;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import com.dearme.backend.dearmebe.common.util.PromptBuilder;
import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PromptBuilderTest {

    @Test
    void 감정_지수에_따라_적절한_프롬프트를_생성한다() {
        List<Memo> memos = List.of(
                Memo.createMemo("client123", LocalDate.now(), EmotionEmoji.SAD, 80, "슬픔", "기분이 안 좋았다."),
                Memo.createMemo("client123", LocalDate.now(), EmotionEmoji.ANGRY, 100, "분노", "짜증났다.")
        );

        String prompt = PromptBuilder.buildPrompt("client123", 90, "기분 나쁨", memos);

        assertThat(prompt).contains("기분 나쁨");
        assertThat(prompt).contains("슬픔");
        assertThat(prompt).contains("위로와 공감");
    }
}
