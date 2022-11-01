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
    @Column(name = "report_user_id")
    private String reportUserId; // 신고자
    @Column(name = "target_user_id")
    private String targetUserId; // 피신고자
    private String content; // 신고 내용
    private LocalDateTime createTime; // 신고 시간

    @Column(name = "reference_idx")
    private int referenceIdx; // 신고 당한 게시글 또는 댓글 번호

    private int type; // 신고 유형

    @Builder
    public Alert(int idx, String reportUserId, String targetUserId, String content, LocalDateTime createTime, int referenceIdx, int type) {
        this.idx = idx;
        this.reportUserId = reportUserId;
        this.targetUserId = targetUserId;
        this.content = content;
        this.createTime = createTime;
        this.referenceIdx = referenceIdx;
        this.type = type;
    }
}
