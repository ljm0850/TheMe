package com.ssafy.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Getter
@Builder
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
