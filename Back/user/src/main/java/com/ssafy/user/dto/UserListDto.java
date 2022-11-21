package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@ToString
public class UserListDto {

    private int idx;
    private String email; // 이메일
    private String id; // 아이디
    private String nickname; // 닉네임
    private String picture; // 프로필 사진
    private LocalDateTime createTime; // 회원가입 시간
    private String description; // 자기소개
    private int alertCount; // 누적신고횟수
    private String[] pictures;
    private int followCount;
    private int boardCount;
    private int commentCount;
    @Builder
    public UserListDto(int idx, String email, String id, String nickname, String picture, LocalDateTime createTime,
                       String description, int alertCount, String[] pictures, int followCount, int boardCount, int commentCount) {
        this.idx = idx;
        this.email = email;
        this.id = id;
        this.nickname = nickname;
        this.picture = picture;
        this.createTime = createTime;
        this.description = description;
        this.alertCount = alertCount;
        this.pictures = pictures;
        this.followCount = followCount;
        this.boardCount = boardCount;
        this.commentCount = commentCount;
    }

}
