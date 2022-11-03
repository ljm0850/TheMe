package com.ssafy.feed.dto.board;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "temp")
@Entity
public class BoardGroupListDto {
    @Id
    int themeIdx; // 테마 idx
    int userIdx; //유저 idx
    String city; // 테마 이름
    String name; // 장소 이름
    String place; // 장소 주소
    Long boardCount; //게시글 갯수

    @Builder
    public BoardGroupListDto(int themeIdx,int userIdx, String city, String name, String place, Long boardCount) {
        this.themeIdx = themeIdx;
        this.userIdx = userIdx;
        this.city = city;
        this.name = name;
        this.place = place;
        this.boardCount = boardCount;
    }
}
