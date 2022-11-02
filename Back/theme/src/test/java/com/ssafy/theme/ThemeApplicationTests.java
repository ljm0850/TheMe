package com.ssafy.theme;

import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.entity.UserTheme;
import com.ssafy.theme.repository.ThemeRepository;
import com.ssafy.theme.repository.UserThemeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	@Autowired
	ThemeApplicationTests(ThemeRepository themeRepository, UserThemeRepository userThemeRepository){
		this.themeRepository = themeRepository;
		this.userThemeRepository = userThemeRepository;
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
				.createTime(LocalDateTime.now())
				.build();

		themeRepository.save(theme);
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
					.theme(userTheme.getTheme())
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
	void 테마검색() {
		String target = "test";

		List<Theme> targetThemeList = themeRepository.searchByTarget(target);
		for(int i=0;i<targetThemeList.size();i++) {
			Theme targetTheme = targetThemeList.get(i);
			System.out.println(targetTheme.toString());
		}
	}
}
