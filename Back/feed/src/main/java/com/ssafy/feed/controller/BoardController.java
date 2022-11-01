package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}