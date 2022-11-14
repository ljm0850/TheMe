package com.ssafy.feed.client;

import com.ssafy.feed.dto.theme.UserThemeDtoWithMSA;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "theme")
public interface ThemeClient {
    @GetMapping("/theme/userThemename/{theme_idx}")
    String getUserThemeName(@PathVariable(name = "theme_idx") int theme_idx);
    @GetMapping("/theme/name/{theme_idx}")
    String getThemeName(@PathVariable(name = "theme_idx") int theme_idx);
    @GetMapping("/theme/userList/{theme_idx}/{user_idx}")
    List<UserThemeDtoWithMSA> getThemeUserList(@PathVariable(name = "theme_idx") int theme_idx,@PathVariable(name = "user_idx") int user_idx);
    @GetMapping("/theme/openType/{following_user_idx}/{follow_theme_idx}")
    int getThemeOpenType(@PathVariable(name = "following_user_idx") int followUserIdx
            , @PathVariable(name = "follow_theme_idx") int followThemeIdx);
    @PostMapping("/theme/isUserTheme/{user_idx}/{theme_idx}")
    int isUserTheme(@PathVariable(name = "user_idx") int userIdx
            , @PathVariable(name = "theme_idx") int ThemeIdx);
    @GetMapping("/theme/userTheme/{user_theme_idx}")
    int whoUserIdx(@PathVariable(name = "user_theme_idx") int userThemeIdx);
    @GetMapping("/theme/isScrap/{user_idx}/{theme_idx}")
    boolean isScrap(@PathVariable(name = "user_idx") int userIdx,@PathVariable(name = "theme_idx") int themeIdx);
    @GetMapping("/theme/userThemeList/{theme_idx}/{user_idx}")
    List<UserThemeDtoWithMSA> getUserThemeUserList(@PathVariable(name = "theme_idx") int theme_idx,@PathVariable(name = "user_idx") int user_idx);
}
