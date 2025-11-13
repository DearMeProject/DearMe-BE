package com.dearme.backend.dearmebe.common.constant;

import com.dearme.backend.dearmebe.global.exception.CustomException;
import com.dearme.backend.dearmebe.global.exception.ErrorCode;

public enum EmotionEmoji {
    HAPPY("😀", 20),
    NEUTRAL("😐", 40),
    SLEEPY("😴", 60),
    SAD("😢", 80),
    ANGRY("😡", 100);

    private final String emoji;
    private final int emotionScore;

    EmotionEmoji(String emoji, int emotionScore) {
        this.emoji = emoji;
        this.emotionScore = emotionScore;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getEmotionScore() {
        return emotionScore;
    }

    public static EmotionEmoji from(String emoji) {
        for (EmotionEmoji e : EmotionEmoji.values()) {
            if (e.getEmoji().equals(emoji)) {
                return e;
            }
        }
        throw new CustomException(ErrorCode.INVALID_EMOJI_TYPE, "유효하지 않은 이모지입니다.");
    }
}

