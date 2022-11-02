package com.ssafy.feed.client;

import com.ssafy.feed.dto.user.UserInfoByIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/user/info/id/{user_idx}")
    UserInfoByIdDto getUserInfo(@PathVariable(name = "user_idx") int userIdx);
}