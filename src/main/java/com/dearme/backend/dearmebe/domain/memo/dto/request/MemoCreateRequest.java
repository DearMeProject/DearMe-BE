package com.dearme.backend.dearmebe.domain.memo.dto.request;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MemoCreateRequest {

    @NotBlank(message = "날짜는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식이 올바르지 않습니다. (YYYY-MM-DD)")
    private String date;

    @NotBlank(message = "감정 이모지는 필수 입력 값입니다.")
    private String emoji;

    @NotNull(message = "감정 점수는 필수 입력 값입니다.")
    @Min(value = 0, message = "감정 점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "감정 점수는 100점 이하여야 합니다.")
    private Integer emotionScore;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    @Size(min = 1, max = 10000, message = "내용은 1자 이상 10000자 이하여야 합니다.")
    private String content;
}
