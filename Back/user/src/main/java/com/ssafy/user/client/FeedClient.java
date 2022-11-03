package com.ssafy.user.client;

import com.ssafy.user.dto.BoardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="feed")
public interface FeedClient {
    @GetMapping("/feed/board/{user_idx}")
    List<BoardDto> userBoardList(int user_idx);
}
