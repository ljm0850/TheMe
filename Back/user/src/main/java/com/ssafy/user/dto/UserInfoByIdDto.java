package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class UserInfoByIdDto {
    int userIdx;
    String email;
    String description;
    String kakaoId;
    String nickname;
    String picture;
    LocalDateTime createTime;

    @Builder
    public UserInfoByIdDto(int userIdx, String email, String description, String kakaoId, String nickname, String picture, LocalDateTime createTime) {
        this.userIdx = userIdx;
        this.email = email;
        this.description = description;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.picture = picture;
        this.createTime = createTime;
    }
}
