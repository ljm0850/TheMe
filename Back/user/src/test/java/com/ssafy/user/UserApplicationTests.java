package com.ssafy.user;

import com.ssafy.user.dto.UserInfoDto;
import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

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
	void 카카오로그인(){
		String kakaoId = "kakaoTokenId2";
		String description = "자기소개";
		String email = "email";
		String nickname = "joe2";
		String picture = "picture";

		if(!userRepository.existsById(kakaoId)){ // 없는 회원이면 회원가입

			User user = User.builder()
					.alertCount(0)
					.createTime(LocalDateTime.now())
					.description(description)
					.email(email)
					.id(kakaoId)
					.nickname(nickname)
					.picture(picture)
					.build();

			userRepository.save(user);
		}

		// 로그인 accessToken, refreshToken 반환


	}

	@Test
	void 회원정보조회(){

		String nickname ="joe";
		User user = userRepository.findByNickname(nickname);

		UserInfoDto userInfoDto = UserInfoDto.builder()
				.nickname(user.getNickname())
				.description(user.getDescription())
				.picture(user.getPicture())
				.build();

		System.out.println(userInfoDto.toString());
	}

	@Test void 닉네임중복조회(){

		String nickname = "joe";
		if(userRepository.existsByNickname(nickname)) System.out.println("중복");
		System.out.println("가능");

	}

	@Test
	void 회원정보수정(){

		int userId = 1;
		String updateNickname = "updateJoe";
		String updateDescription = "updateDescription";
		String updatePicture = "updatePicture";

		User user = userRepository.findById(userId)
				.orElseThrow(IllegalAccessError::new);

		user.updateNickname(updateNickname);
		user.updateDescription(updateDescription);
		user.updatePicture(updatePicture);

		System.out.println(user.toString());
	}

	@Test
	void 탈퇴하기() {
		String nickname = "joe";

		User user = userRepository.findByNickname(nickname);
		userRepository.delete(user);
	}

	@Test
	void 팔로우하기() {
		int user_id = 2;
		int target_user_id = 3;
		int theme_id = 3;

		User followingUser = userRepository.findById(user_id)
				.orElseThrow(IllegalAccessError::new);
		User followUser = userRepository.findById(target_user_id)
				.orElseThrow(IllegalAccessError::new);

		Follow addFollow = Follow.builder()
				.followUser(followUser)
				.followingUser(followingUser)
				.themeIdx(theme_id)
				.build();

		followRepository.save(addFollow);
	}

	@Test
	void 팔로우취소() {
		Follow targetFollow = followRepository.findById(1)
				.orElseThrow(IllegalAccessError::new);

		followRepository.delete(targetFollow);
	}

	@Test
	void 팔로우한테마() {
		int user_id = 2;
		User targetUser = userRepository.findById(user_id)
				.orElseThrow(IllegalAccessError::new);
		List<Integer> followingThemeIdList = followRepository.findThemeIdByFollowingUser(targetUser);

		for(int i=0;i<followingThemeIdList.size();i++)
			System.out.println(followingThemeIdList.get(i));
	}

	@Test
	void 팔로워리스트() {
		int user_id = 3 ;
		User targetUser = userRepository.findById(user_id).orElseThrow(IllegalAccessError::new);

		List<Integer> followerList = followRepository.findFollowerByUser(targetUser);
		for(int i=0;i<followerList.size();i++) {
			int userIdx = followerList.get(i);
			User follower = userRepository.findById(userIdx).orElseThrow(IllegalAccessError::new);

			System.out.println(follower.getNickname());
		}
	}

	@Test
	void 팔로잉리스트() {
		int user_id = 2;
		User targetUser = userRepository.findById(user_id).orElseThrow(IllegalAccessError::new);

		List<Integer> followingIdxList = followRepository.findFollowingByUser(targetUser);
		for(int i=0;i<followingIdxList.size();i++) {
			int userIdx = followingIdxList.get(i);
			User following = userRepository.findById(userIdx).orElseThrow(IllegalAccessError::new);

			System.out.println(following.getNickname());
		}
	}
}
