package com.ssafy.theme;

import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.repository.ThemeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class ThemeApplicationTests {
	ThemeRepository themeRepository;
	@Autowired
	ThemeApplicationTests(ThemeRepository themeRepository){
		this.themeRepository = themeRepository;
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

		Theme theme = new Theme();
		theme.setName(name);
		theme.setEmoticon(emoticon);
		theme.setCreateTime(createTime);
		themeRepository.save(theme);


		Optional<Theme> themeOptional = themeRepository.findById(1);


		if(themeOptional.isPresent()){
			System.out.println(themeOptional.get().getEmoticon());
		}
	}
}
