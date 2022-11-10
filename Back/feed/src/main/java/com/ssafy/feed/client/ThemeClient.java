package com.ssafy.feed.client;

import com.ssafy.feed.dto.theme.UserThemeDtoWithMSA;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "theme")
public interface ThemeClient {
    @GetMapping("/theme/name/{theme_idx}")
    String getThemeName(@PathVariable(name = "theme_idx") int theme_idx);
    @GetMapping("/theme/userList/{theme_idx}")
    List<UserThemeDtoWithMSA> getThemeUserList(@PathVariable(name = "theme_idx") int theme_idx);
    @GetMapping("/theme/openType/{follow_user_idx}/{follow_theme_idx}")
    int getThemeOpenType(@PathVariable(name = "follow_user_idx") int followUserIdx
            , @PathVariable(name = "follow_theme_idx") int followThemeIdx);
    @PostMapping("/theme/isUserTheme/{user_idx}/{theme_idx}")
    int isUserTheme(@PathVariable(name = "user_idx") int userIdx
            , @PathVariable(name = "theme_idx") int ThemeIdx);
}
