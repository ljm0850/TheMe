package com.ssafy.user.service;

import com.ssafy.user.dto.UserDto;
import com.ssafy.user.dto.UserFollowThemeDto;
import com.ssafy.user.entity.User;

import java.util.List;

public interface FollowService {
    int followTheme(int theme_id, int user_id, int target_user_id);
    void cancelFollow(int user_theme_id, int user_id);
    List<Integer> getFollowingThemeList(int user_id);
    List<UserDto> getFollowingList(int user_id);
    List<UserDto> getFollowerList(int user_id);
    void cancelUserFollow(int target_user_id, int user_id);

    List<UserFollowThemeDto> getUserFollowTheme(int userIdx);
    List<Integer> getRecommendThemeList();

    boolean isFollow(int user_idx, int target_user_idx, int theme_idx);
}
