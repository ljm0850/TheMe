package com.ssafy.theme.service;

import com.ssafy.theme.dto.theme.ThemeDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.dto.theme.ThemeRegistDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ThemeService {
    void registTheme(ThemeRegistDto themeRegistDto);
    void createUserTheme(UserThemeDto userThemeDto);
    List<UserThemeDto> getThemeList(int user_id);
    ResponseEntity<?> getUserInfo(String nickname);

    List<ThemeDto> searchTheme(String target);
}
