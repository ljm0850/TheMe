package com.ssafy.etc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
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
}
