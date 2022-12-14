package com.ssafy.theme.dto.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@ToString
@NoArgsConstructor
public class UserThemeRegistDto {

    private int userIdx;
    private int themeIdx; // 부모테마
    private int openType; // 공개여부 (0비공개,1친구공개,2검색허용)
    private LocalDateTime createTime; // 생성시간
    private LocalDateTime modifyTime; // 수정시간
    private boolean challenge; // 챌린지 여부
    private String description; // 테마 소개

    @Builder
    public UserThemeRegistDto(int userIdx, int themeIdx, int openType, LocalDateTime createTime, LocalDateTime modifyTime, boolean challenge, String description) {
        this.userIdx = userIdx;
        this.themeIdx = themeIdx;
        this.openType = openType;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.challenge = challenge;
        this.description = description;
    }
}
