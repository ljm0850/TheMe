package com.ssafy.feed.client;

import com.ssafy.feed.dto.user.UserFollowThemeDto;
import com.ssafy.feed.dto.user.UserInfoByIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/user/info/id/{user_idx}")
    UserInfoByIdDto getUserInfo(@PathVariable(name = "user_idx") int userIdx);
    @GetMapping("/user/following/themeList/{user_idx}")
    List<UserFollowThemeDto> getUserFollowTheme(@RequestParam(name = "user_idx") int userIdx);
    @PostMapping("/user/alertCount/{user_idx}")
    String alertUser(@PathVariable(name = "user_idx") int userIdx);
}