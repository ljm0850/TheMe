package com.ssafy.user.service;


import com.ssafy.user.dto.*;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserInfoByIdDto loginUser(String kakaoToken);
    UserInfoDto getUserInfo(String nickname,int userIdx);
    boolean isPossibleNickname(String nickname);
    void updateUser(String nickname, UserUpdateDto userUpdate);
    void deleteUser(String nickname);
    UserInfoByIdDto getUserInfoById(int userIdx);
    void alertUser(int userIdx);
    List<UserListDto> searchRecommend();

    List<String> liveSearchUser(String value);

    Map<String, Object> searchThemeInfo(String value);
    BoardInfoForUserDto boardInfoForUser(int themeIdx, int userIdx);

    void deleteFollowUserTheme(int theme_idx);
}