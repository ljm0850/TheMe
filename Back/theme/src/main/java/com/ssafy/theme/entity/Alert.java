package com.ssafy.theme.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne
    @JoinColumn(name = "report_user_id")
    private User reportUser; // 신고자
    @ManyToOne
    @JoinColumn(name = "target_user_id")
    private User targetUser; // 피신고자
    private String content; // 신고 내용
    private LocalDateTime createTime; // 신고 시간
    @Column(name = "reference_idx")
    private int referenceIdx; // 신고 당한 게시글 또는 댓글 번호
    private int type; // 신고 유형
}
