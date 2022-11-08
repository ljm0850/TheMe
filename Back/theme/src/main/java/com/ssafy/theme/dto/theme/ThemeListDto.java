package com.ssafy.theme.dto.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class ThemeListDto {
    private String name;
    private LocalDateTime createTime;
    private String emoticon;
    private boolean isBookMarked; // 북마크 여부
    private int boardCount; // 총 게시물 수
    private int personCount; // 참여한 사람의 수
    private String[] pictures; // 대표 사진
    @Builder
    public ThemeListDto(String name, LocalDateTime createTime, String emoticon, boolean isBookMarked, int boardCount, int personCount, String[] pictures) {
        this.name = name;
        this.createTime = createTime;
        this.emoticon = emoticon;
        this.isBookMarked = isBookMarked;
        this.boardCount = boardCount;
        this.personCount = personCount;
        this.pictures = pictures;
    }
}
