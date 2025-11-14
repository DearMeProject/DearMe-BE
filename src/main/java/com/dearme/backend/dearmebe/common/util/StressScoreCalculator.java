package com.dearme.backend.dearmebe.common.util;

import com.dearme.backend.dearmebe.domain.memo.entity.Memo;

import java.util.List;

public class StressScoreCalculator {

    public static String classifyTone(int stressScore) {
        if (stressScore <= 40) return "좋음";
        if (stressScore <= 70) return "보통";
        return "기분나쁨";
    }

    public static int calculateAverage(List<Memo> memos) {
        if (memos == null || memos.isEmpty()) {
            return 0;
        }

        double avg = memos.stream()
                .mapToInt(Memo::getEmotionScore)
                .average()
                .orElse(0.0);

        return (int) Math.round(avg);
    }
}
