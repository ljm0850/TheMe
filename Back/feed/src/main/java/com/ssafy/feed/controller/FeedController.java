package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.BoardDto;
import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.board.BoardListDto;
import com.ssafy.feed.dto.board.BoardSimpleListDto;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feed")
public class FeedController {
    private final static String OK = "success";
    private final static String FAIL = "fail";
    FeedService feedService;
    @Autowired
    FeedController(FeedService feedService){
        this.feedService = feedService;
    }
    @GetMapping("/region")
    @ApiOperation(value = "메인피드 지역 목록" , notes = "0 : 전국, 1 : 서울, 2 : 대전, 3 : 광주, 4 : 구미, 5 : 부울경(부산,울산,경남)")
    public ResponseEntity<?> feedByRegion(@RequestParam(name = "region") int region, HttpServletRequest request,
                                          @RequestParam(name="pageSize") int pageSize, @RequestParam(name ="pageIdx")int pageIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            List<BoardSimpleListDto> data = feedService.feedByRegion(userIdx,region,pageIdx,pageSize); // 불러와야해요 리스트
            result.put("data",data);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
    @GetMapping("/map/theme/{theme_idx}")
    @ApiOperation(value = "해당 테마에 대한 게시글 목록" , notes = "테마 번호를 토대로 게시글 목록을 리스팅")
    public ResponseEntity<?> themeBoardGroup(HttpServletRequest request,@PathVariable(name = "theme_idx") int theme_idx,@RequestParam(name="pageSize") int pageSize, @RequestParam(name ="pageIdx")int pageIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            List<BoardGroupListDto> boardGroupListDto = feedService.themeBoardGroup(userIdx,theme_idx,pageIdx,pageSize);
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
    @GetMapping("/map/place/{theme_idx}")
    @ApiOperation(value = "해당 주소에 대한 게시글 목록" , notes = "테마 번호를 토대로 게시글 목록을 리스팅")
    public ResponseEntity<?> themeBoardList(@PathVariable(name = "theme_idx") int theme_idx,@RequestParam(name="name") String name,
                                            @RequestParam(name="pageSize") int pageSize, @RequestParam(name ="pageIdx")int pageIdx, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            List<BoardSimpleListDto> boardSimpleListDtoList = feedService.themeBoardList(theme_idx,name,pageIdx,pageSize,userIdx);
            result.put("data",boardSimpleListDtoList);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e);
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/board/list/{user_idx}")
    @ApiOperation(value = "user_idx가 등록한 게시글 리스트" , notes = "유저를 토대로 게시글 목록을 리스팅")
    public List<BoardDto> userBoardList(@PathVariable(name = "user_idx") int user_idx) {
        return feedService.getUserBoardList(user_idx);
    }
    @GetMapping("/map/userTheme/{userTheme_idx}")
    @ApiOperation(value = "유저 테마에 대한 게시글 목록")
    public ResponseEntity<?> userThemeList(HttpServletRequest request,@PathVariable(name = "userTheme_idx") int userThemeIdx,
                                           @RequestParam(name="pageSize") int pageSize, @RequestParam(name ="pageIdx")int pageIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            // 유저 테마에 대한 게시글 목록
            List<BoardGroupListDto> boardGroupListDto = feedService.userThemeList(userThemeIdx,pageIdx,pageSize,userIdx);
            result.put("data",boardGroupListDto);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
            System.out.println(e);
        }
        return new ResponseEntity<>(result, status);
    }
}
