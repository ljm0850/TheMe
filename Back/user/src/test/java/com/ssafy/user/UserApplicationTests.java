package com.ssafy.user;

import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApplicationTests {
	UserRepository userRepository;
	FollowRepository followRepository;
	@Autowired
	public UserApplicationTests(UserRepository userRepository, FollowRepository followRepository) {
		this.userRepository = userRepository;
		this.followRepository = followRepository;
	}



	@Test
	void contextLoads() {
	}

	@Test
	void 팔로우하기() {
		int user_id = 1;
		int target_user_id = 2;

		int theme_id = 1;

		User followingUser = userRepository.findById(user_id).orElse(new User());
		User followUser = userRepository.findById(target_user_id).orElse(new User());

		Follow addFollow = Follow.builder()
				.followUser(followUser)
				.followingUser(followingUser)
				.themeIdx(theme_id)
				.build();

		followRepository.save(addFollow);
	}

	@Test
	void 팔로우취소() {
		Follow targetFollow = followRepository.findById(1).orElse(new Follow());

		followRepository.delete(targetFollow);
	}
}
