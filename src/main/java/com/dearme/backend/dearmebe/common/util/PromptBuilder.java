package com.dearme.backend.dearmebe.common.util;

import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptBuilder {

    public static String buildPrompt(String nickname, int stressScore, String tone, List<Memo> memos) {
        StringBuilder sb = new StringBuilder();

        sb.append("당신은 따뜻하고 공감 능력이 뛰어난 상담사입니다.\n");
        sb.append("사용자가 작성한 감정 메모를 기반으로 감정의 흐름, 스트레스 패턴, 주요 감정 키워드를 분석하세요.\n");
        sb.append("그리고 아래 요구사항을 반드시 지켜서 답변을 생성하세요:\n");
        sb.append("1) '").append(nickname).append("'님을 직접 언급하며 답변 시작\n");
        sb.append("2) 오늘의 스트레스 지수(정확히 ").append((int) stressScore).append("점) 기반으로 감정 상태 설명\n");
        sb.append("3) 3~4문장 길이의 위로 + 공감 + 조언 메시지 작성\n");
        sb.append("4) 마지막 줄에 힐링 콘텐츠 2가지 이상 추천 (예: 음악, 명언, 책, 유튜브 영상 링크 제공)\n");
        sb.append("5) 말투는 존댓말로 따뜻하게\n\n");

        sb.append("------ 사용자가 최근 작성한 메모 ------\n");
        for (Memo m : memos) {
            sb.append("- (").append(m.getDate()).append(") ")
                    .append(m.getEmoji().getEmoji()).append(" ")
                    .append(m.getContent())
                    .append("\n");
        }

        sb.append("\n--- 감정 분석 데이터 ---\n");
        sb.append("\n사용자의 평균 감정 점수는 ")
                .append(stressScore)
                .append("이며, 전반적인 감정 상태는 '")
                .append(tone)
                .append("'입니다.\n");
        sb.append("위의 정보를 충분히 고려하여 최종 상담 메시지를 생성하세요.\n");

        sb.append(switch (tone) {
            case "좋음" -> """
                    사용자는 최근 긍정적인 감정을 느끼고 있습니다.
                    응원과 격려가 담긴 메시지를 3~4문장으로 작성하고,
                    그에 어울리는 힐링 콘텐츠(명언, 유튜브, 음악)를 추천해주세요.
                    말투는 존댓말이며, 밝고 긍정적인 어조로 답해주세요.
                    """;
            case "보통" -> """
                    사용자는 약간의 피로감이나 감정 기복을 느끼고 있습니다.
                    공감과 응원이 담긴 메시지를 3~4문장으로 작성하고,
                    마음의 안정에 도움이 되는 콘텐츠를 함께 제안해주세요.
                    말투는 존댓말이며, 차분하고 부드러운 어조로 답해주세요.
                    """;
            default -> """
                    사용자는 최근 스트레스, 슬픔, 분노 등 부정적인 감정을 느끼고 있습니다.
                    진심 어린 위로와 공감을 담은 메시지를 3~4문장으로 작성하고,
                    따뜻한 위로가 되는 힐링 콘텐츠를 함께 추천해주세요.
                    말투는 존댓말이며, 따뜻하고 진정성 있게 답해주세요.
                    """;
        });

        return sb.toString();
    }
}
