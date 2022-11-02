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
public class ThemeDto {
    private int idx;

    private String name;
    private LocalDateTime createTime;
    private String emoticon;

    @Builder
    public ThemeDto(int idx, String name, LocalDateTime createTime, String emoticon) {
        this.idx = idx;
        this.name = name;
        this.createTime = createTime;
        this.emoticon = emoticon;
    }
}
