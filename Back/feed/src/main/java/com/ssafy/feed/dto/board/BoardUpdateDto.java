package com.ssafy.feed.dto.board;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class BoardUpdateDto {
    String name;
    String place;
    String description;
    int themeIdx;
    String[] pictures;
    @Builder
    public BoardUpdateDto(String name, String place, String description, int themeIdx, String[] pictures){
        this.name = name;
        this.place = place;
        this.description = description;
        this.themeIdx = themeIdx;
        this.pictures = pictures;
    }
}
