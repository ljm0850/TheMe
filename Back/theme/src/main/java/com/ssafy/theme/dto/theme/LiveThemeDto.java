package com.ssafy.theme.dto.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class LiveThemeDto {
    private String name; // 테마 이름
    private boolean isMy; // 유저테마에 있는지 여부

    @Builder
    public LiveThemeDto(String name, boolean isMy) {
        this.name = name;
        this.isMy = isMy;
    }
}
