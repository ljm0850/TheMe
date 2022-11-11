package com.ssafy.theme.dto.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@ToString
@NoArgsConstructor
public class PublicThemeListDto {
    private int idx;//공용테마idx
    private String name; // 테마 제목
    private String emoticon; // 테마 이모티콘
    private LocalDateTime createTime; // 생성시간
    private long userCount; // 참여한 유저 수
    private boolean isBookmarked ; // 공용 테마 북마크 여부

    @Builder
    public PublicThemeListDto(int idx, String name, String emoticon, LocalDateTime createTime, long userCount,boolean isBookmarked) {
        this.idx = idx;
        this.name = name;
        this.emoticon = emoticon;
        this.createTime = createTime;
        this.userCount = userCount;
        this.isBookmarked = isBookmarked;
    }
}
