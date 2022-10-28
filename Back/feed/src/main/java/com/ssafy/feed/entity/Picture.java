package com.ssafy.feed.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne
    @JoinColumn(name = "board_idx")
    private Board board; // 사진 올리는 게시글 번호
    private String picture; // 사진

    @Builder
    public Picture(int idx, Board board, String picture) {
        this.idx = idx;
        this.board = board;
        this.picture = picture;
    }
}
