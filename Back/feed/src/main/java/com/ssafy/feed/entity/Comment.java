package com.ssafy.feed.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;
    @Column(name = "user_idx")
    private int userIdx; // 댓글 작성자
    @ManyToOne
    @JoinColumn(name = "board_idx")
    private Board board; // 댓글의 게시물 번호
    private String content; // 댓글 내용
    private int alertCount; // 댓글 신고 횟수

    @Builder
    public Comment(int idx, int userIdx, Board board, String content, int alertCount) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.board = board;
        this.content = content;
        this.alertCount = alertCount;
    }
}
