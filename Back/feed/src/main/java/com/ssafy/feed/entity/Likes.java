package com.ssafy.feed.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @OneToOne
    @JoinColumn(name = "board_idx")
    private Board board; // 좋아요 하는 게시글 번호
    private int userIdx; // 좋아요 누른 사용자

    @Builder
    public Likes(int idx, Board board, int userIdx) {
        this.idx = idx;
        this.board = board;
        this.userIdx = userIdx;
    }
}
