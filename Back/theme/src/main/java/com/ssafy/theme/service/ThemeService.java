package com.ssafy.theme.service;

import com.ssafy.theme.dto.theme.*;
import com.ssafy.theme.entity.UserTheme;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface ThemeService {
    int registTheme(ThemeRegistDto themeRegistDto,int userIdx);
    int createUserTheme(int userIdx, UserThemeRegistDto userThemeRegistDto);
    List<UserThemeDto> getThemeList(int user_id);
    ResponseEntity<?> getUserInfo(String nickname);
    String deleteBoardAndComment(@PathVariable(name = "theme_idx") int theme_idx);

    ResponseEntity<?> getUserIdxInfo(int userIdx);

    List<PublicThemeListDto> getPublicThemeList(int userIdx,int sort, int pageSize, int pageIdx);
    List<SearchThemeDto> searchTheme(String target,int userIdx);

    void scrapTheme(int user_id, int theme_idx);

    List<UserThemeDto> followThemeList(UserThemeIdxDto userThemeIdxDto);

    List<PublicThemeListDto> getBookmarkThemeList(int userIdx);
    List<LiveThemeDto> liveSearchTheme(String value,int userIdx);

    String getThemeName(int theme_idx);

    Map<String, Object> searchThemeInfo(String value, int userIdx);

    List<UserThemeDtoWithMSA> getThemeUserList(int theme_idx,int user_idx);

    void deleteScrapTheme(int user_id, int theme_idx);

    List<RecommendDto> getRecommendThemeList();

    List<UserThemeDto> getUserThemeByUserIdx(int user_idx);

    int getThemeOpenType(int followUserIdx, int followThemeIdx);
    BoardInfoDto boardInfoByTheme(int themeIdx);

    ThemeDto getPublicThemeDetail(int theme_idx, int user_idx);
    UserThemeDetailDto getUserThemeDetail(int user_idx, int theme_idx);

    int isUserTheme(int userIdx, int themeIdx);

    int whoUserIdx(int userThemeIdx);
    boolean isFollow(int user_idx, int target_user_idx, int theme_idx);
    String deleteFollowUserTheme(@PathVariable(name = "theme_idx") int theme_idx);
    boolean isScrap(int userIdx, int themeIdx);
    List<UserThemeDtoWithMSA> getUserThemeUserList(int userThemeIdx,int user_idx);

    String getUserThemeName(int theme_idx);

    int modifyTheme(Integer themeIdx, Integer openType, int userIdx);

    int deleteTheme(Integer themeIdx, int userIdx);
}
