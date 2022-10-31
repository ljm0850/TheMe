package com.ssafy.user.service.impl;

import com.ssafy.user.dto.UserInfoDto;
import com.ssafy.user.dto.UserLoginDto;
import com.ssafy.user.dto.UserUpdateDto;
import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import com.ssafy.user.service.FollowService;
import com.ssafy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    FollowRepository followRepository;
    FollowService followService;

    @Autowired
    UserServiceImpl(UserRepository userRepository, FollowRepository followRepository, FollowService followService) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.followService = followService;
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) { // 로그인
        if(!userRepository.existsById(userLoginDto.getKakaoId())){ // 없는 회원이면 회원가입

            User user = User.builder()
                    .alertCount(0)
                    .createTime(LocalDateTime.now())
                    .description("자기소개")
                    .email("email")
                    .id(userLoginDto.getKakaoId())
                    .nickname("nickname")
                    .picture("사진")
                    .build();

            userRepository.save(user);
        }

        // 로그인 accessToken, refreshToken 반환

    }

    @Override
    public UserInfoDto getUserInfo(String nickname) { // 마이페이지 조회

        User user = userRepository.findByNickname(nickname);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .nickname(user.getNickname())
                .description(user.getDescription())
                .picture(user.getPicture())
                .follower(followService.getFollowerList(user.getIdx()).size())
                .following(followService.getFollowingList(user.getIdx()).size())
                .build();

        // 내가 쓴 테마 리스트
        // 내가 쓴 게시글 수
        // 내가 팔로우한 테마 리스트


        return userInfoDto;
    }

    @Override
    public boolean isPossibleNickname(String nickname) { // 닉네임 중복검사
        // true이면 사용가능, false일 때 이미 있는 닉네임
        return !userRepository.existsByNickname(nickname);
    }

    @Override
    public void updateUser(String nickname, UserUpdateDto userUpdate) { // 회원정보 수정

        User user = userRepository.findByNickname(nickname);

        user.updateNickname(userUpdate.getNickname());
        user.updateDescription(userUpdate.getDescription());
        user.updatePicture(userUpdate.getPicture());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String nickname) { // 회원탈퇴
        User user = userRepository.findByNickname(nickname);

        List<Follow> followList = followRepository.findByFollowUserOrFollowingUser(user, user);

        for(int i=0; i<followList.size(); i++){
            followRepository.delete(followList.get(i));
        }

        userRepository.delete(user);
    }
}
