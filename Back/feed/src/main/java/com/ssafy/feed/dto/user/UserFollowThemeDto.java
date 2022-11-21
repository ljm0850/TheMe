package com.ssafy.feed.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserFollowThemeDto {
    int followUserIdx;
    int followThemeIdx;
    @Builder
    public UserFollowThemeDto(int followUserIdx,int followThemeIdx){
        this.followUserIdx = followUserIdx;
        this.followThemeIdx = followThemeIdx;
    }
}
