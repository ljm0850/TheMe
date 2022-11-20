package com.ssafy.feed.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;
    private int reportUserIdx; // 신고자
    private int targetUserIdx; // 피신고자
    private String content; // 신고 내용
    private LocalDateTime createTime; // 신고 시간

    @Column(name = "reference_idx")
    private int referenceIdx; // 신고 당한 게시글 또는 댓글 번호

    private int type; // 신고 유형

    @Builder
    public Alert(int idx, int reportUserIdx, int targetUserIdx, String content, LocalDateTime createTime, int referenceIdx, int type) {
        this.idx = idx;
        this.reportUserIdx = reportUserIdx;
        this.targetUserIdx = targetUserIdx;
        this.content = content;
        this.createTime = createTime;
        this.referenceIdx = referenceIdx;
        this.type = type;
    }
}
