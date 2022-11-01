package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.service.BoardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/feed")
public class BoardController {
    private final static String OK = "success";
    private final static String FAIL = "fail";

    BoardService boardService;

    @Autowired
    BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    @ApiOperation(value = "게시글 등록")
    public ResponseEntity<?> registBoard(HttpServletRequest request, @RequestBody BoardRegistDto boardRegistDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String userId = (String) request.getAttribute("userId");
        try {
            boardService.registBoard(userId, boardRegistDto);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }

    @DeleteMapping("/board/{board_idx}")
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<?> deleteBoard(@PathVariable(name = "board_idx") int boardIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            boardService.deleteBoard(boardIdx);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
    @PutMapping("/board/{board_idx}")
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<?> updateBoard(@PathVariable(name = "board_idx") int boardIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {

            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
}