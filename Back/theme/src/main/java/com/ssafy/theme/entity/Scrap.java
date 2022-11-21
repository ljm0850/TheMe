package com.ssafy.theme.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne
    @JoinColumn(name = "theme_idx")
    private Theme theme; // 스크랩 할 테마 번호
    @Column(name = "user_idx") // 다대다 연관관계 애매해서 컬럼으로만 두기
    private int userIdx; // 스크랩 하는 사용자

    @Builder
    public Scrap(int idx, Theme theme, int userIdx) {
        this.idx = idx;
        this.theme = theme;
        this.userIdx = userIdx;
    }
}
