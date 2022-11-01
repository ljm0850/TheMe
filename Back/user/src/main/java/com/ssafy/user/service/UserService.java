package com.ssafy.user.service;


import com.ssafy.user.dto.UserInfoByIdDto;
import com.ssafy.user.dto.UserInfoDto;
import com.ssafy.user.dto.UserUpdateDto;

public interface UserService {
    void loginUser(String kakaoToken);
    UserInfoDto getUserInfo(String nickname);
    boolean isPossibleNickname(String nickname);
    void updateUser(String nickname, UserUpdateDto userUpdate);
    void deleteUser(String nickname);
    UserInfoByIdDto getUserInfoById(int userIdx);
}