package com.ssafy.etc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @OneToOne
    @JoinColumn(name = "board_idx")
    private Board board; // 좋아요 하는 게시글 번호
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; // 좋아요 누른 사용자
}
