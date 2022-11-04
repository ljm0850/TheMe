package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
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
    public ResponseEntity<?> registComment(@PathVariable(name = "board_idx") int boardIdx, HttpServletRequest request, @RequestParam(name = "content") String content) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boolean is = commentService.registComment(boardIdx, userIdx,content);
            result.put("data", is); // 댓글 등록 여부
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
    @DeleteMapping("/comment/{comment_idx}")
    @ApiOperation(value = "댓글 삭제")
    public ResponseEntity<?> deleteComment(HttpServletRequest request, @PathVariable(name = "comment_idx") int commentIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boolean is = commentService.deleteComment(userIdx,commentIdx);
            result.put("data", is); // 댓글 삭제 여부
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
    @PostMapping("/comment/alert/{comment_idx}")
    @ApiOperation(value = "댓글 신고" , notes = "param에 댓글 신고 내용")
    public ResponseEntity<?> alertComment(@PathVariable(name = "comment_idx") int commentIdx, @RequestParam(name = "content") String content, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boolean is = commentService.alertComment(userIdx,commentIdx,content);
            result.put("data", is); // 같은 신고자가 같은 댓글을 한번만 신고가능
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
}
