package com.ssafy.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    private String email; // 이메일
    private String id; // 아이디

    @Column(unique = true)
    private String nickname; // 닉네임

    private String picture; // 프로필 사진
    private LocalDateTime createTime; // 회원가입 시간
    private String description; // 자기소개
    private int alertCount; // 누적신고횟수

    @Builder
    public User(int idx, String email, String id, String nickname, String picture, LocalDateTime createTime, String description, int alertCount) {
        this.idx = idx;
        this.email = email;
        this.id = id;
        this.nickname = nickname;
        this.picture = picture;
        this.createTime = createTime;
        this.description = description;
        this.alertCount = alertCount;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updatePicture(String picture) {
        this.picture = picture;
    }

    public void updateAlertCount(){this.alertCount += 1;}
}
