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
    int userIdx;
    String nickname;
    String description;
    String picture;

    int posts;
    int themes;
    int follower;
    int following;

    List<UserThemeListDto> themeDtoList;
    List<UserThemeListDto> followingDtoList;

    @Builder
    public UserInfoDto(int userIdx, String nickname, String description, String picture, int posts, int themes, int follower, int following, List<UserThemeListDto> themeDtoList, List<UserThemeListDto> followingDtoList) {
        this.userIdx = userIdx;
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
