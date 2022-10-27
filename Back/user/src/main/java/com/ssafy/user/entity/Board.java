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
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne
    @JoinColumn(name = "theme_idx")
    private Theme theme; // 게시글 해당 테마 번호
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 게시글 작성자
    private String name; // 게시글 장소 이름
    private String place; // 게시글 장소 주소
    private String description; // 게시글 장소 설명
    private LocalDateTime createTime; // 생성 시간
    private LocalDateTime modifyTime; // 수정 시간
    private String city; // 게시글 장소 지역
    private int alertCount; // 게시글 신고 횟수
}
