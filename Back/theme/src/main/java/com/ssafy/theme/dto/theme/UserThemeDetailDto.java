package com.ssafy.theme.dto.theme;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class UserThemeDetailDto {

    private int themeIdx;
    private int userThemeIdx;
    private String name;
    private String emoticon;
    private boolean isMine;
    private boolean isFollow;

    @Builder
    public UserThemeDetailDto(int themeIdx, int userThemeIdx, String name, String emoticon, boolean isMine, boolean isFollow) {
        this.themeIdx = themeIdx;
        this.userThemeIdx = userThemeIdx;
        this.name = name;
        this.emoticon = emoticon;
        this.isMine = isMine;
        this.isFollow = isFollow;
    }
}
