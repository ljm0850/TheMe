package com.ssafy.user.service;


import com.ssafy.user.dto.UserDto;
import com.ssafy.user.dto.UserInfoByIdDto;
import com.ssafy.user.dto.UserInfoDto;
import com.ssafy.user.dto.UserUpdateDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserInfoByIdDto loginUser(String kakaoToken);
    UserInfoDto getUserInfo(String nickname);
    boolean isPossibleNickname(String nickname);
    void updateUser(String nickname, UserUpdateDto userUpdate);
    void deleteUser(String nickname);
    UserInfoByIdDto getUserInfoById(int userIdx);
    void alertUser(int userIdx);
    List<UserDto> searchRecommend();

    List<String> liveSearchUser(String value);

    Map<String, Object> searchThemeInfo(String value);
}