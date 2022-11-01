package com.ssafy.theme.service.impl;

import com.ssafy.theme.client.UserClient;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.dto.theme.ThemeRegistDto;
import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.entity.UserTheme;
import com.ssafy.theme.repository.ThemeRepository;
import com.ssafy.theme.repository.UserThemeRepository;
import com.ssafy.theme.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ThemeServiceImpl implements ThemeService {
    ThemeRepository themeRepository;
    UserThemeRepository userThemeRepository;
    UserClient userClient;
    @Autowired
    ThemeServiceImpl(ThemeRepository themeRepository,
                     UserThemeRepository userThemeRepository,
                     UserClient userClient)
    {
        this.themeRepository = themeRepository;
        this.userThemeRepository = userThemeRepository;
        this.userClient = userClient;
    }
    @Override
    public void registTheme(ThemeRegistDto themeRegistDto) {

        //builder 사용법
        Theme theme = Theme.builder()
                .name(themeRegistDto.getName())
                .emoticon(themeRegistDto.getEmoticon())
                .createTime(LocalDateTime.now())
                .build();

        themeRepository.save(theme);
    }
    @Override
    public void createUserTheme(UserThemeDto userThemeDto) {
        UserTheme userTheme = UserTheme.builder()
                .theme(userThemeDto.getTheme())
                .userIdx(userThemeDto.getUserIdx())
                .createTime(userThemeDto.getCreateTime())
                .challenge(userThemeDto.isChallenge())
                .description(userThemeDto.getDescription())
                .modifyTime(userThemeDto.getModifyTime())
                .openType(userThemeDto.getOpenType())
                .build();

        userThemeRepository.save(userTheme);
    }
    @Override
    public List<UserThemeDto> getThemeList(int user_id) {
        List<UserThemeDto> result = new ArrayList<>();

        List<UserTheme> themeList = userThemeRepository.findByUserIdx(user_id);

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

        return result;
    }

    @Override
    public ResponseEntity<?> getUserInfo(String nickname) {
        ResponseEntity<?> userInfo = userClient.getUserInfo(nickname);

        return userInfo;
    }
}
