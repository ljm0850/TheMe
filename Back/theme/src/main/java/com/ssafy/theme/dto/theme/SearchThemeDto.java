package com.ssafy.theme.dto.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class SearchThemeDto {
    private int themeIdx;
    private String name;
    private LocalDateTime createTime;
    private String emoticon;
    private int openType;

    @Builder
    public SearchThemeDto(int themeIdx, String name, LocalDateTime createTime, String emoticon, int openType) {
        this.themeIdx = themeIdx;
        this.name = name;
        this.createTime = createTime;
        this.emoticon = emoticon;
        this.openType = openType;
    }
}
