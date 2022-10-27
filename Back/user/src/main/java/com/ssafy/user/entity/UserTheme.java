package com.ssafy.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class UserTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 테마 작성자
    @ManyToOne
    @JoinColumn(name = "parent_theme_name")
    private Theme theme; // 부모테마
    private int openType; // 공개여부 (0비공개,1친구공개,2검색허용)
    private LocalDateTime createTime; // 생성시간
    private LocalDateTime modifyTime; // 수정시간
    private String emoticon; // 이모티콘
    private boolean challenge; // 챌린지 여부
    private String description; // 테마 소개
}
