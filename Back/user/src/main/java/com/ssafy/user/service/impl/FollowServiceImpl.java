package com.ssafy.user.service.impl;

import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import com.ssafy.user.service.FollowService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService
{
    UserRepository userRepository;
    FollowRepository followRepository;

    public FollowServiceImpl(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    @Override
    public void followTheme(int theme_id, int user_id, int target_user_id) {
        User followingUser = userRepository.findById(user_id).orElse(new User());
        User followUser = userRepository.findById(target_user_id).orElse(new User());

        Follow addFollow = Follow.builder()
                .followUser(followUser)
                .followingUser(followingUser)
                .themeIdx(theme_id)
                .build();

        followRepository.save(addFollow);
    }

    @Override
    public void cancelFollow(int follow_id) {
        Follow targetFollow = followRepository.findById(follow_id).orElse(new Follow());

        followRepository.delete(targetFollow);
    }
}
