package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.board.BoardListDto;
import com.ssafy.feed.dto.comment.CommentListDto;
import com.ssafy.feed.service.FeedService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feed")
public class FeedController {
    private final static String OK = "success";
    private final static String FAIL = "fail";
    FeedService feedService;
    @Autowired
    FeedController(FeedService feedService){
        this.feedService = feedService;
    }
    @GetMapping("/map/theme/{theme_idx}")
    @ApiOperation(value = "해당 테마에 대한 게시글 목록" , notes = "테마 번호를 토대로 게시글 목록을 리스팅")
    public ResponseEntity<?> themeBoardGroup(@PathVariable(name = "theme_idx") int theme_idx,@RequestParam(name="pageSize") int pageSize, @RequestParam(name ="pageIdx")int pageIdx, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = 5;
        try {
            List<BoardGroupListDto> boardGroupListDto = feedService.themeBoardGroup(theme_idx,pageIdx,pageSize);
            result.put("data",boardGroupListDto);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e);
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
}
