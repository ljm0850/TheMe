package com.ssafy.etc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column(name = "theme_idx") // 다대다 연관관계 애매해서 컬럼으로만 두기
    private int themeIdx; // 스크랩 할 테마 번호
    @Column(name = "user_id") // 다대다 연관관계 애매해서 컬럼으로만 두기
    private String userId; // 스크랩 하는 사용자
}
