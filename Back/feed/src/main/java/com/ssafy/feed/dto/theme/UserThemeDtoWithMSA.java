package com.ssafy.feed.dto.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class UserThemeDtoWithMSA {
    private int idx; // 부모테마 idx
    private int userThemeIdx; //개인테마 idx
    private int userIdx; // 테마 작성자
    private String themeTitle; // 부모테마
    private String themeEmoticon; //
    private int openType; // 공개여부 (0비공개,1친구공개,2검색허용)
    private LocalDateTime createTime; // 생성시간
    private LocalDateTime modifyTime; // 수정시간
    private boolean challenge; // 챌린지 여부
    private String description; // 테마 소개

    @Builder
    public UserThemeDtoWithMSA(int idx, int userThemeIdx,int userIdx, String themeTitle, String themeEmoticon, int openType, LocalDateTime createTime,
                               LocalDateTime modifyTime, boolean challenge, String description) {
        this.idx = idx;
        this.userThemeIdx = userThemeIdx;
        this.userIdx = userIdx;
        this.themeTitle = themeTitle;
        this.themeEmoticon = themeEmoticon;
        this.openType = openType;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.challenge = challenge;
        this.description = description;
    }
}
