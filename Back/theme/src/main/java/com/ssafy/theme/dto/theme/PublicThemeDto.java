package com.ssafy.theme.dto.theme;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "test")
@Getter
public class PublicThemeDto {
    @Id
    private int idx;
    private String title; // 테마 제목
    private String emoticon; // 테마 이모티콘
    private LocalDateTime createTime; // 생성시간
    private long userCount; // 참여한 유저 수


    @Builder
    public PublicThemeDto(int idx, String title, String emoticon, LocalDateTime createTime, long userCount) {
        this.idx = idx;
        this.title = title;
        this.emoticon = emoticon;
        this.createTime = createTime;
        this.userCount = userCount;
    }
}
