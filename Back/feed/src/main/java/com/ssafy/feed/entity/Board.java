package com.ssafy.feed.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;
    @Column(name = "theme_idx")
    private int themeIdx; // 게시글 해당 테마 번호
    @Column(name = "user_idx")
    private int userIdx; // 게시글 작성자
    private String name; // 게시글 장소 이름
    private String place; // 게시글 장소 주소
    private String description; // 게시글 장소 설명
    private LocalDateTime createTime; // 생성 시간
    private LocalDateTime modifyTime; // 수정 시간
    private String city; // 게시글 장소 지역
    private int alertCount; // 게시글 신고 횟수

    @Builder
    public Board(int idx, int themeIdx, int userIdx, String name, String place, String description, LocalDateTime createTime, LocalDateTime modifyTime, String city, int alertCount) {
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
    public void updateName(String name){ this.name = name; }
    public void updatePlace(String place) { this.place = place; }
    public void updateDescription(String description) { this.description = description; }
    public void updateThemeIdx(int themeIdx) { this.themeIdx = themeIdx; }
    public void updateCity(String city) { this.city = city; }
    public void updateTime(LocalDateTime modifyTime) { this.modifyTime = modifyTime; }
    public void updateAlertCount(int alertCount) { this.alertCount = alertCount; }
}
