package com.ssafy.user.service;


import com.ssafy.user.dto.UserLoginDto;

public interface UserService {
    void loginUser(UserLoginDto userLoginDto);

}