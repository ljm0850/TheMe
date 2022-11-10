package com.ssafy.user.client;

import com.ssafy.user.dto.UserThemeDto;
import com.ssafy.user.dto.UserThemeListDto;
import com.ssafy.user.dto.UserThemeIdxDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="theme")
public interface ThemeClient {

    @GetMapping("/theme/list/{user_idx}")
    List<UserThemeDto> getUserThemeByUserIdx(@PathVariable(name = "user_idx") int user_idx);

    @PostMapping("/theme/follow")
    List<UserThemeDto> followThemeList(@RequestBody UserThemeIdxDto userThemeIdxDto);
}
