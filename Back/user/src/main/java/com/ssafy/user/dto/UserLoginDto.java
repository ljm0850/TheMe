package com.ssafy.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class UserLoginDto {
    String kakaoToken;
    public UserLoginDto(String kakaoToken){
        this.kakaoToken = kakaoToken;
    }
}
