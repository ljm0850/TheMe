package com.ssafy.theme.service;

import com.ssafy.theme.dto.theme.PublicThemeDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.dto.theme.ThemeRegistDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ThemeService {
    void registTheme(ThemeRegistDto themeRegistDto);
    void createUserTheme(UserThemeDto userThemeDto);
    List<UserThemeDto> getThemeList(int user_id);
    ResponseEntity<?> getUserInfo(String nickname);

    ResponseEntity<?> getUserIdxInfo(int userIdx);

    List<PublicThemeDto> getPublicThemeList(int isMarked, int sort, int userIdx,int pageSize,int pageIdx);
}
