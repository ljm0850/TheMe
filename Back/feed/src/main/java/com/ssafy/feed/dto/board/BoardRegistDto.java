package com.ssafy.feed.dto.board;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class BoardRegistDto {
    String name;
    String place;
    String description;
    int themeIdx;

    @Builder
    public BoardRegistDto(String name, String place, String description, int themeIdx){
        this.name = name;
        this.place = place;
        this.description = description;
        this.themeIdx = themeIdx;
    }
}
