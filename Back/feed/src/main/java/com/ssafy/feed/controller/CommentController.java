package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/feed")
public class CommentController {
    private final static String OK = "success";
    private final static String FAIL = "fail";

    CommentService commentService;
    @Autowired
    CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/comment/{board_idx}")
    @ApiOperation(value = "댓글 등록", notes = "param에 댓글 등록 내용")
    public ResponseEntity<?> registComment(HttpServletRequest request, @RequestParam(name = "content") String content) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = (int) request.getAttribute("userIdx");
        try {
            boolean is = commentService.registComment(userIdx,content);
            result.put("data", is); // 댓글 등록 여부
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }

}
