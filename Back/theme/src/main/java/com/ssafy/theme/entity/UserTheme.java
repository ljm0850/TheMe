package com.ssafy.theme.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class UserTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    private int userIdx; // 테마 작성자
    @ManyToOne
    @JoinColumn(name = "parent_theme_idx")
    private Theme theme; // 부모테마
    private int openType; // 공개여부 (0비공개,1친구공개,2검색허용)
    private LocalDateTime createTime; // 생성시간
    private LocalDateTime modifyTime; // 수정시간
    private boolean challenge; // 챌린지 여부
    private String description; // 테마 소개

    @Builder
    public UserTheme(int idx, int userIdx, Theme theme, int openType, LocalDateTime createTime, LocalDateTime modifyTime, boolean challenge, String description) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.theme = theme;
        this.openType = openType;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.challenge = challenge;
        this.description = description;
    }
}
