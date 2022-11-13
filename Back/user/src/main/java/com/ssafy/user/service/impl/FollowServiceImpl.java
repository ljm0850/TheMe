package com.ssafy.user.service.impl;

import com.ssafy.user.dto.UserDto;
import com.ssafy.user.dto.UserFollowThemeDto;
import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import com.ssafy.user.service.FollowService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public int followTheme(int theme_id, int user_id, int target_user_id) { // 테마 팔로우
        User followingUser = userRepository.findById(target_user_id)
                .orElseThrow(IllegalAccessError::new);

        User followUser = userRepository.findById(user_id)
                .orElseThrow(IllegalAccessError::new);

        // 이 유저테마 존재 여부 확인하는 api


        Follow addFollow = Follow.builder()
                .followUser(followUser)
                .followingUser(followingUser)
                .themeIdx(theme_id)
                .build();

        Follow save = followRepository.save(addFollow);
        return save.getIdx();
    }

    @Override
    public void cancelFollow(int theme_idx, int user_id) { // 테마 팔로우 취소
        Optional<User> user = userRepository.findById(user_id);
        Follow targetFollow = followRepository.findByThemeIdxAndFollowUser(theme_idx, user.get())
                .orElseThrow(IllegalAccessError::new);

        followRepository.delete(targetFollow);
    }

    @Override
    public List<Integer> getFollowingThemeList(int user_id) {
        User targetUser = userRepository.findById(user_id)
                .orElseThrow(IllegalAccessError::new);
        List<Integer> followingThemeIdList = followRepository.findThemeIdByFollowingUser(targetUser);

        return followingThemeIdList;
    }

    @Override
    public List<UserDto> getFollowingList(int user_id) {
        List<UserDto> followList = new ArrayList<>();
        User targetUser = userRepository.findById(user_id).orElseThrow(IllegalAccessError::new);

        List<Integer> followingIdxList = followRepository.findFollowingByUser(targetUser);
        for(int i=0;i<followingIdxList.size();i++) {
            int userIdx = followingIdxList.get(i);
            User following = userRepository.findById(userIdx).orElseThrow(IllegalAccessError::new);

            UserDto userDto = UserDto.builder()
                    .nickname(following.getNickname())
                    .email(following.getEmail())
                    .picture(following.getPicture())
                    .description(following.getDescription())
                    .idx(following.getIdx())
                    .build();

            followList.add(userDto);
        }
        return followList;
    }

    @Override
    public List<UserDto> getFollowerList(int user_id) {
        List<UserDto> followList = new ArrayList<>();
        User targetUser = userRepository.findById(user_id).orElseThrow(IllegalAccessError::new);

        List<Integer> followingIdxList = followRepository.findFollower(targetUser);
        for(int i=0;i<followingIdxList.size();i++) {
            int userIdx = followingIdxList.get(i);
            User following = userRepository.findById(userIdx).orElseThrow(IllegalAccessError::new);

            UserDto userDto = UserDto.builder()
                    .nickname(following.getNickname())
                    .email(following.getEmail())
                    .picture(following.getPicture())
                    .description(following.getDescription())
                    .idx(following.getIdx())
                    .build();

            followList.add(userDto);
            //followList.add(following.getNickname());
        }


        return followList;
    }

    @Override
    public void cancelUserFollow(int target_user_id, int user_id) {
        User followerUser = userRepository.findById(target_user_id).orElseThrow(IllegalAccessError::new);
        User followingUser = userRepository.findById(user_id).orElseThrow(IllegalAccessError::new);

        List<Follow> followingByUser = followRepository.findByFollowUserAndFollowingUser(followingUser,followerUser);
        for(int i=0;i<followingByUser.size();i++) {
            followRepository.delete(followingByUser.get(i));
        }
    }

    @Override
    public List<UserFollowThemeDto> getUserFollowTheme(int userIdx) {
        List<UserFollowThemeDto> userFollowThemeDtoList = new ArrayList<>();
        Optional<User> user = userRepository.findById(userIdx);
        List<Follow> followList = followRepository.findByFollowingUser(user.get());
        for(int i=0;i<followList.size();i++){
            UserFollowThemeDto userFollowThemeDto = UserFollowThemeDto.builder()
                    .followUserIdx(followList.get(i).getFollowUser().getIdx())
                    .followThemeIdx(followList.get(i).getThemeIdx())
                    .build();
            userFollowThemeDtoList.add(userFollowThemeDto);
        }
        return userFollowThemeDtoList;
    }
    public List<Integer> getRecommendThemeList() {
        return followRepository.countByThemeIdx();
    }

    @Override
    public boolean isFollow(int user_idx, int target_user_idx, int theme_idx) {
        Optional<User> user = userRepository.findById(user_idx);
        Optional<User> targetUser = userRepository.findById(target_user_idx);
        Optional<Follow> follow = followRepository.findByFollowingUserAndFollowUserAndThemeIdx(targetUser.get(),user.get(),theme_idx);
        return follow.isPresent();
    }
}
