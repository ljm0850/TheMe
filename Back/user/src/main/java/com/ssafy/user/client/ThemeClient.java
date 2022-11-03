package com.ssafy.user.client;

import com.ssafy.user.dto.ThemeDto;
import com.ssafy.user.dto.UserThemeDto;
import com.ssafy.user.dto.UserThemeIdxDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@FeignClient(name="theme")
public interface ThemeClient {

    @GetMapping("/theme/list/{user_id}")
    List<UserThemeDto> getUserThemeByUserIdx(int user_idx);

    @PostMapping("/theme/follow")
    List<UserThemeDto> followThemeList(UserThemeIdxDto userThemeIdxDto);
}
