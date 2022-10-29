package com.ssafy.user.service;

public interface FollowService {
    void followTheme(int theme_id, int user_id, int target_user_id);
    void cancelFollow(int follow_id);
}
