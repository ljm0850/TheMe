package com.ssafy.user.service;


import com.ssafy.user.dto.UserInfoDto;
import com.ssafy.user.dto.UserLoginDto;
import com.ssafy.user.dto.UserUpdateDto;

public interface UserService {
    void loginUser(UserLoginDto userLoginDto);
    UserInfoDto getUserInfo(String nickname);
    boolean isPossibleNickname(String nickname);
    void updateUser(String nickname, UserUpdateDto userUpdate);
    void deleteUser(String nickname);
}