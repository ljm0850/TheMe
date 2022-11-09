package com.ssafy.user.client;

import com.ssafy.user.dto.BoardDto;
import com.ssafy.user.dto.BoardInfoForUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="feed")
public interface FeedClient {
    @GetMapping("/feed/board/list/{user_idx}")
    List<BoardDto> userBoardList(@PathVariable(name = "user_idx") int user_idx);

    @GetMapping("/board/themeInfo/{theme_idx}/{user_idx}")
    BoardInfoForUserDto boardInfoForUser(@PathVariable(name = "theme_idx") int themeIdx, @PathVariable(name = "user_idx") int userIdx);
}