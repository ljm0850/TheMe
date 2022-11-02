package com.ssafy.theme.dto.theme;

import com.ssafy.theme.entity.Theme;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class PublicThemeDto {
    private int idx;
    private String title; // 테마 제목
    private String emoticon; // 테마 이모티콘
    private int userIdx; // 테마 작성자 idx
    private String userNickname; // 테마 작성자 닉네임
    private LocalDateTime createTime; // 생성시간
    private int userCount; // 참여한 유저 수


    @Builder
    public PublicThemeDto(int idx, String title, String emoticon, int userIdx, String userNickname, LocalDateTime createTime, int userCount) {
        this.idx = idx;
        this.title = title;
        this.emoticon = emoticon;
        this.userIdx = userIdx;
        this.userNickname = userNickname;
        this.createTime = createTime;
        this.userCount = userCount;
    }
}
