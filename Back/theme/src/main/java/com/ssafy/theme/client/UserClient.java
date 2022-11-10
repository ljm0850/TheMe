package com.ssafy.theme.client;

import com.ssafy.theme.dto.theme.UserThemeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/user/info/{nickname}")
    ResponseEntity<?> getUserInfo(@PathVariable(name = "nickname") String nickname);
    @GetMapping("/user/info/id/{user_idx}")
    ResponseEntity<?> getUserIdxInfo(@PathVariable(name = "user_idx") int userIdx);
    @GetMapping("/userList/{theme_idx}")
    List<UserThemeDto> getThemeUserList(@PathVariable(name = "theme_idx") int theme_idx);
    @GetMapping("/user/follow/recommend")
    List<Integer> getRecommendThemeList();
    @GetMapping("/isFollow/{user_idx}/{target_user_idx}/{theme_idx}")
    boolean isFollow(@PathVariable(name = "user_idx") int user_idx,
                     @PathVariable(name = "target_user_idx") int target_user_idx,
                     @PathVariable(name = "theme_idx") int theme_idx);
}
