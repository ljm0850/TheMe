package com.ssafy.theme;

import com.ssafy.theme.client.UserClient;
import com.ssafy.theme.dto.theme.PublicThemeDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.entity.Scrap;
import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.entity.UserTheme;
import com.ssafy.theme.repository.ScrapRepository;
import com.ssafy.theme.repository.ThemeRepository;
import com.ssafy.theme.repository.UserThemeRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ThemeApplicationTests {
	ThemeRepository themeRepository;
	UserThemeRepository userThemeRepository;
	ScrapRepository scrapRepository;
	UserClient userClient;
	@Autowired
	ThemeApplicationTests(ThemeRepository themeRepository, UserThemeRepository userThemeRepository,ScrapRepository scrapRepository, UserClient userClient){
		this.themeRepository = themeRepository;
		this.userThemeRepository = userThemeRepository;
		this.scrapRepository = scrapRepository;
		this.userClient = userClient;
	}
	@Test
	void contextLoads() {
		System.out.println("test");
	}
	@Test
	void 테마등록(){
		String name = "test";
		LocalDateTime createTime = LocalDateTime.now();
		String emoticon = "🎈";

		Theme theme = Theme.builder()
				.name(name)
				.emoticon(emoticon)
				.createTime(createTime)
				.build();
		themeRepository.save(theme);
		// 내가 만든 테마이므로 유저테마에도 등록
		UserTheme userTheme = UserTheme.builder()
				.userIdx(10)
				.challenge(false)
				.createTime(theme.getCreateTime())
				.modifyTime(theme.getCreateTime())
				.theme(theme)
				.build();
		userThemeRepository.save(userTheme);
		System.out.println(userTheme.getTheme().getName());
		System.out.println(userTheme.getUserIdx());
		System.out.println(userTheme.getCreateTime());
	}

	@Test
	void 유저테마등록() {
		Theme theme = themeRepository.findByIdx(1);

		UserTheme userTheme = UserTheme.builder()
				.theme(theme)
				.userIdx(2)
				.createTime(LocalDateTime.now())
				.challenge(false)
				.description("asdfsadf")
				.modifyTime(LocalDateTime.now())
				.openType(1)
				.build();

		userThemeRepository.save(userTheme);
	}

	@Test
	void 유저가만든테마들() {
		List<UserThemeDto> result = new ArrayList<>();

		List<UserTheme> themeList = userThemeRepository.findByUserIdx(2);

		for(int i=0;i<themeList.size();i++) {
			UserTheme userTheme = themeList.get(i);

			UserThemeDto target = UserThemeDto.builder()
					.idx(userTheme.getIdx())
					.themeIdx(userTheme.getTheme().getIdx())
					.challenge(userTheme.isChallenge())
					.description(userTheme.getDescription())
					.modifyTime(userTheme.getModifyTime())
					.createTime(userTheme.getCreateTime())
					.openType(userTheme.getOpenType())
					.build();

			result.add(target);
		}
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i).toString());
	}
	@Test
	void 공용테마목록조회_전체_인기순(){
		int pageSize = 1;
		int pageIdx = 0;
		Pageable pageable = PageRequest.of(pageIdx, pageSize);
		Slice<PublicThemeDto> themeList = userThemeRepository.getPopularAllThemeListWithJPA( pageable);
		for(PublicThemeDto publicThemeDto : themeList){
			System.out.println(publicThemeDto.getTitle());
		}
	}
	@Test
	void 공용테마목록조회_전체_시간순(){
		int pageSize = 1;
		int pageIdx = 0;
		Pageable pageable = PageRequest.of(pageIdx, pageSize);
		Slice<PublicThemeDto> themeList = userThemeRepository.getRecnetAllThemeListWithJPA( pageable);
		for(PublicThemeDto publicThemeDto : themeList){
			System.out.println(publicThemeDto.getTitle());
		}
	}
	@Test
	void 공용테마목록조회_북마크_전체조회() {
		int userIdx = 2;
		List<Scrap> themeList = scrapRepository.findByUserIdx(userIdx);

		for (Scrap publicThemeDto : themeList) {
			System.out.println(publicThemeDto.getTheme().getIdx());
			Long themeCount = userThemeRepository.getThemeCountWithJPA(publicThemeDto.getTheme().getIdx());
			System.out.println(themeCount);
			System.out.println(publicThemeDto.getTheme().getName());
		}
	}
	@Test
	void 테마검색() {
		String target = "test";

		List<Theme> targetThemeList = themeRepository.searchByTarget(target);
		for(int i=0;i<targetThemeList.size();i++) {
			Theme targetTheme = targetThemeList.get(i);
			System.out.println(targetTheme.toString());
		}
	}

	@Test
	void 즐겨찾기() {
		int theme_idx = 1;
		int user_id = 2;
		Theme theme = themeRepository.findByIdx(theme_idx);
		Scrap scrap = Scrap.builder()
				.theme(theme)
				.userIdx(user_id)
				.build();

		scrapRepository.save(scrap);
	}

	@Test
	void 즐겨찾기삭제() {
		int theme_idx = 1;
		int user_id = 2;

		Theme targetTheme = themeRepository.findByIdx(theme_idx);
		if(scrapRepository.existsByThemeAndUserIdx(targetTheme, user_id)) {
			Scrap scrap = scrapRepository.findByThemeAndUserIdx(targetTheme, user_id).orElseThrow(IllegalAccessError::new);

			scrapRepository.delete(scrap);
			System.out.println(true);
		} else System.out.println(false);
	}

	@Test
	void 팔로우하는테마() {
		List<UserThemeDto> result = new ArrayList<>();

		List<Integer> userThemeList = new ArrayList<>();
		userThemeList.add(1);
		userThemeList.add(2);
		for(int i=0; i<userThemeList.size();i++) {

			int userThemeIdx = userThemeList.get(i);
			System.out.println("userThemeIdx : " + userThemeIdx);
			UserTheme userTheme = userThemeRepository.findById(userThemeIdx).orElseThrow(IllegalAccessError::new);

			UserThemeDto userThemeDto = UserThemeDto.builder()
					.themeIdx(userTheme.getTheme().getIdx())
					.userIdx(userTheme.getUserIdx())
					.createTime(userTheme.getCreateTime())
					.challenge(userTheme.isChallenge())
					.description(userTheme.getDescription())
					.modifyTime(userTheme.getModifyTime())
					.openType(userTheme.getOpenType())
					.idx(userTheme.getIdx())
					.build();

			result.add(userThemeDto);
		}

		for(int i=0;i<result.size();i++) {
			System.out.println(result.get(i).toString());
		}
	}

	@Test
	void 실시간테마검색() {
		List<String> strings = themeRepository.liveSearchByName("test");
		for(int i=0;i<strings.size();i++) {
			System.out.println(strings.get(i));
		}

	}
	@Test
	void 테마_idx로_유저_테마목록_받아오기(){
		int themeIdx = 2;
		Theme theme = themeRepository.findByIdx(themeIdx);
		System.out.println(theme.getName());
		List<UserTheme> userThemeList = userThemeRepository.findByTheme(theme);
		for(UserTheme userTheme : userThemeList){
			if(userTheme.getOpenType()==1){
				System.out.println(userTheme.getDescription());
			}
		}
	}

	@Test
	void 추천테마목록_팔로워순() {
		List<UserThemeDto> result = new ArrayList<>();

		List<Integer> recommendList = userClient.getRecommendThemeList();
		for(int i=0;i<recommendList.size();i++) {
			UserTheme userTheme = userThemeRepository.findById(recommendList.get(i)).orElseThrow(IllegalAccessError::new);

			UserThemeDto userThemeDto = UserThemeDto.builder()
					.themeIdx(userTheme.getTheme().getIdx())
					.userIdx(userTheme.getUserIdx())
					.openType(userTheme.getOpenType())
					.createTime(userTheme.getCreateTime())
					.description(userTheme.getDescription())
					.modifyTime(userTheme.getModifyTime())
					.challenge(userTheme.isChallenge())
					.idx(userTheme.getIdx())
					.build();

			result.add(userThemeDto);
			System.out.println(userThemeDto.toString());
		}
	}

	@Test
	void 특정유저의게시물목록_Feign() {
		List<UserTheme> userThemeList = userThemeRepository.findByUserIdx(9);

		List<UserThemeDto> result = new ArrayList<>();

		for(int i=0;i<userThemeList.size();i++) {
			UserTheme targetUserTheme = userThemeList.get(i);
			UserThemeDto userThemeDto = UserThemeDto.builder()
					.idx(targetUserTheme.getIdx())
					.userIdx(targetUserTheme.getUserIdx())
					.themeIdx(targetUserTheme.getTheme().getIdx())
					.modifyTime(targetUserTheme.getModifyTime())
					.createTime(targetUserTheme.getCreateTime())
					.challenge(targetUserTheme.isChallenge())
					.openType(targetUserTheme.getOpenType())
					.description(targetUserTheme.getDescription())
					.build();

			result.add(userThemeDto);
			System.out.println(userThemeDto.toString());
		}

	}
}
