package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserUpdateDto {
    String nickname;
    String description;
    String picture;

    @Builder
    public UserUpdateDto(String nickname, String description, String picture) {
        this.nickname = nickname;
        this.description = description;
        this.picture = picture;
    }
}
