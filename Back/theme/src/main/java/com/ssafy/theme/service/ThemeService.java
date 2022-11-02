package com.ssafy.theme.service;

import com.ssafy.theme.dto.theme.PublicThemeDto;
import com.ssafy.theme.dto.theme.ThemeDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.dto.theme.ThemeRegistDto;
import com.ssafy.theme.dto.theme.UserThemeIdxDto;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ThemeService {
    void registTheme(ThemeRegistDto themeRegistDto);
    void createUserTheme(UserThemeDto userThemeDto);
    List<UserThemeDto> getThemeList(int user_id);
    ResponseEntity<?> getUserInfo(String nickname);

    ResponseEntity<?> getUserIdxInfo(int userIdx);

    List<PublicThemeDto> getPublicThemeList(int sort, int pageSize, int pageIdx);
    List<ThemeDto> searchTheme(String target);

    void scrapTheme(int user_id, int theme_idx);

    List<UserThemeDto> followThemeList(UserThemeIdxDto userThemeIdxDto);

    List<PublicThemeDto> getBookmarkThemeList(int userIdx);
    List<String> liveSearchTheme(String value);

    String getThemeName(int theme_idx);
}
