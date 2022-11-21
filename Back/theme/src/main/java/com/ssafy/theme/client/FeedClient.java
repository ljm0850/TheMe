package com.ssafy.theme.client;

import com.ssafy.theme.dto.theme.BoardInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "feed")
public interface FeedClient {
    @GetMapping("/feed/board/themeList/{theme_idx}")
    BoardInfoDto boardInfoByTheme(@PathVariable(name = "theme_idx") int themeIdx);
    @DeleteMapping("/feed/deleteBoardAndComment/{theme_idx}")
    String deleteBoardAndComment(@PathVariable(name = "theme_idx") int theme_idx);
}
