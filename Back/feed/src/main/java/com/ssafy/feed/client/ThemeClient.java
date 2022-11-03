package com.ssafy.feed.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "theme")
public interface ThemeClient {
    @GetMapping("/theme/name/{theme_idx}")
    String getThemeName(@PathVariable(name = "theme_idx") int theme_idx);
}
