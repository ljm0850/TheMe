package com.ssafy.feed.controller;

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
    @GetMapping("/region")
    @ApiOperation(value = "메인피드 지역 목록" , notes = "0 : 전국, 1 : 서울, 2 : 대전, 3 : 광주, 4 : 구미, 5 : 부울경(부산,울산,경남)")
    public ResponseEntity<?> feedByRegion(@RequestParam(name = "region") int region, HttpServletRequest request,
                                          @RequestParam(name="pageSize") int pageSize, @RequestParam(name ="pageIdx")int pageIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        //int userIdx = (int) request.getAttribute("userIdx");
        int userIdx = 5;
        try {
            //feedService.feedByRegion(userIdx,region,pageIdx,pageSize); // 불러와야해요 리스트
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
}
