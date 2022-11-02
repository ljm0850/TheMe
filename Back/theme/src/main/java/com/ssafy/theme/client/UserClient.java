package com.ssafy.theme.client;

import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/user/info/{nickname}")
    ResponseEntity<?> getUserInfo(@PathVariable(name = "nickname") String nickname);
    @GetMapping("/user/info/id/{user_idx}")
    ResponseEntity<?> getUserIdxInfo(@PathVariable(name = "user_idx") int userIdx);
}
