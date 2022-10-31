package com.ssafy.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class UserLoginDto {
    String kakaoId;
    public UserLoginDto(String kakaoId){
        this.kakaoId = kakaoId;
    }
}
