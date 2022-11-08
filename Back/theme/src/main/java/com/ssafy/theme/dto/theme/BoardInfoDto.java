package com.ssafy.theme.dto.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BoardInfoDto {
    int boardCount; // 총 게시물
    String[] pictures; // 대표 사진들

    @Builder
    public BoardInfoDto(int boardCount, String[] pictures) {
        this.boardCount = boardCount;
        this.pictures = pictures;
    }
}
