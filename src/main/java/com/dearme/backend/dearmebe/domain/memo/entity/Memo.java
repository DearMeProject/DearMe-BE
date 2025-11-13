package com.dearme.backend.dearmebe.domain.memo.entity;

import com.dearme.backend.dearmebe.common.constant.EmotionEmoji;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "memos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false, length = 255)
    private String clientId;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmotionEmoji emoji;

    @Column(nullable = false)
    private int emotionScore;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    // 기본 생성용 팩토리 (서비스 코드 이용)
    public static Memo createMemo(String clientId, LocalDate date, EmotionEmoji emoji, int emotionScore, String title, String content) {
        Memo memo = new Memo();
        memo.clientId = clientId;
        memo.date = date;
        memo.emoji = emoji;
        memo.emotionScore = emotionScore;
        memo.title = title;
        memo.content = content;
        return memo;
    }

    // 오버로딩된 테스트용 팩토리 (id 지정 가능)
    public static Memo createMemo(Long id, String clientId, LocalDate date, EmotionEmoji emoji, int emotionScore, String title, String content) {
        Memo memo = new Memo();
        memo.id = id;
        memo.clientId = clientId;
        memo.date = date;
        memo.emoji = emoji;
        memo.emotionScore = emotionScore;
        memo.title = title;
        memo.content = content;
        return memo;
    }

    public boolean isOwner(String clientId) {
        return this.clientId.equals(clientId);
    }
}


