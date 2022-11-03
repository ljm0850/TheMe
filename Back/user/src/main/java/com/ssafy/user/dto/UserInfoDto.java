package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@ToString
public class UserInfoDto {
    String nickname;
    String description;
    String picture;

    int posts;
    int themes;
    int follower;
    int following;

    List<UserThemeDto> themeDtoList;
    List<UserThemeDto> followingDtoList;

    @Builder
    public UserInfoDto(String nickname, String description, String picture, int posts, int themes, int follower, int following, List<UserThemeDto> themeDtoList, List<UserThemeDto> followingDtoList) {
        this.nickname = nickname;
        this.description = description;
        this.picture = picture;
        this.posts = posts;
        this.themes = themes;
        this.follower = follower;
        this.following = following;
        this.themeDtoList = themeDtoList;
        this.followingDtoList = followingDtoList;
    }
}
