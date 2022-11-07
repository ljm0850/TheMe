package com.ssafy.theme.dto.theme;

import com.ssafy.theme.entity.Theme;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class RecommendDto {
    private int idx;
    private int userIdx;
    private String name;
    private int themeIdx; // 부모테마
    private int openType; // 공개여부 (0비공개,1친구공개,2검색허용)
    private LocalDateTime createTime; // 생성시간
    private LocalDateTime modifyTime; // 수정시간
    private boolean challenge; // 챌린지 여부
    private String description; // 테마 소개

    @Builder
    public RecommendDto(int idx, String name, int userIdx, int themeIdx, int openType, LocalDateTime createTime,
                        LocalDateTime modifyTime, boolean challenge, String description) {
        this.idx = idx;
        this.name = name;
        this.userIdx = userIdx;
        this.themeIdx = themeIdx;
        this.openType = openType;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.challenge = challenge;
        this.description = description;
    }
}
