package com.ssafy.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class UserLoginDto {
    String name;
    String emoticon;
    public UserLoginDto(String n, String e){
        this.name = n;
        this.emoticon = e;
    }
}
