package com.ssafy.feed.client;

import com.ssafy.feed.dto.theme.ThemeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ThemeClient {
    @GetMapping("/theme/list/{user_id}")
    List<ThemeDto> getThemeList(@PathVariable String user_id);
}
