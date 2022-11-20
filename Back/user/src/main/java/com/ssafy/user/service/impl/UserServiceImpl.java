package com.ssafy.user.service.impl;

import com.ssafy.user.dto.UserLoginDto;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import com.ssafy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    FollowRepository followRepository;
    @Autowired
    UserServiceImpl(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }
    @Override
    public void loginUser(UserLoginDto userLoginDto) {

    }

}
