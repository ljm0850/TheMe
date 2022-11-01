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

    @ManyToOne
    @JoinColumn(name = "board_idx")
    private Board board; // 좋아요 하는 게시글 번호
    @Column(name = "user_id")
    private String userId; // 좋아요 누른 사용자

    @Builder
    public Likes(int idx, Board board, String userId) {
        this.idx = idx;
        this.board = board;
        this.userId = userId;
    }
}
