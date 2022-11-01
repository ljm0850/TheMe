package com.ssafy.user.client;

import com.ssafy.user.dto.ThemeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="theme")
public interface ThemeClient {

    @GetMapping("/theme/list/{user_id}")
    List<ThemeDto> getThemeList(@PathVariable("user_id") String user_id);
}
