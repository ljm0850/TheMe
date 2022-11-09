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
    String[] pictures;
    String latitude;
    String logitude;
    @Builder
    public BoardRegistDto(String name, String place, String description, int themeIdx, String[] pictures
                        ,String latitude,String logitude){
        this.name = name;
        this.place = place;
        this.description = description;
        this.themeIdx = themeIdx;
        this.pictures = pictures;
        this.latitude = latitude;
        this.logitude = logitude;
    }
}
