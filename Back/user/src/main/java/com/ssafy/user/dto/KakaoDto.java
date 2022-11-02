package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class KakaoDto {
    String id;
    String nickname;
    String picture;

    @Builder
    public KakaoDto(String id, String nickname, String picture) {
        this.id = id;
        this.nickname = nickname;
        this.picture = picture;
    }
}
