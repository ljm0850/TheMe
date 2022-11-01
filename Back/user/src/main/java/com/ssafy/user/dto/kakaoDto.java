package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class kakaoDto {
    String id;
    String email;
    String nickname;
    String picture;

    @Builder
    public kakaoDto(String id, String email, String nickname, String picture) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.picture = picture;
    }
}
