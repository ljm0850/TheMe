package com.ssafy.user.service;

import com.ssafy.user.entity.User;

import java.util.List;

public interface FollowService {
    void followTheme(int theme_id, int user_id, int target_user_id);
    void cancelFollow(int follow_id);
    List<Integer> getFollowingThemeList(int user_id);
    List<String> getFollowingList(int user_id);
    List<String> getFollowerList(int user_id);
    void cancelUserFollow(int target_user_id, int user_id);
    List<Integer> getRecommendThemeList();
}
