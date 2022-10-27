package com.ssafy.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 댓글 작성자
    @ManyToOne
    @JoinColumn(name = "board_idx")
    private Board board; // 댓글의 게시물 번호
    private String content; // 댓글 내용
    private int alertCount; // 댓글 신고 횟수
}
