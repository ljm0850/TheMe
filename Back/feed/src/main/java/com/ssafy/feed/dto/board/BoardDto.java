package com.ssafy.feed.dto.board;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class BoardDto {
    private int idx;
    private int themeIdx; // 게시글 해당 테마 번호
    private int userIdx; // 게시글 작성자
    private String name; // 게시글 장소 이름
    private String place; // 게시글 장소 주소
    private String description; // 게시글 장소 설명
    private LocalDateTime createTime; // 생성 시간
    private LocalDateTime modifyTime; // 수정 시간
    private String city; // 게시글 장소 지역
    private int alertCount; // 게시글 신고 횟수

    @Builder
    public BoardDto(int idx, int themeIdx, int userIdx, String name, String place, String description, LocalDateTime createTime, LocalDateTime modifyTime, String city, int alertCount) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.userIdx = userIdx;
        this.name = name;
        this.place = place;
        this.description = description;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.city = city;
        this.alertCount = alertCount;
    }
}
