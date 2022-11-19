package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.*;
import com.ssafy.feed.dto.comment.CommentListDto;
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
import java.util.List;
import java.util.Map;

@RestController
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
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            int boardIdx = boardService.registBoard(userIdx, boardRegistDto);
            result.put("data",boardIdx);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
            System.out.println(e);
        }
        return new ResponseEntity<>(result, status);
    }

    @DeleteMapping("/board/{board_idx}")
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<?> deleteBoard(HttpServletRequest request, @PathVariable(name = "board_idx") int boardIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boardService.deleteBoard(userIdx, boardIdx);
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
    public ResponseEntity<?> updateBoard(HttpServletRequest request, @PathVariable(name = "board_idx") int boardIdx, @RequestBody BoardUpdateDto boardUpdateDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boolean data = boardService.updateBoard(userIdx, boardIdx,boardUpdateDto);
            result.put("data", data);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
    @PostMapping("/board/like/{board_idx}")
    @ApiOperation(value = "게시글 좋아요")
    public ResponseEntity<?> likesBoard(@PathVariable(name = "board_idx") int boardIdx, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boardService.likesBoard(userIdx,boardIdx);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
    @DeleteMapping("/board/like/{board_idx}")
    @ApiOperation(value = "게시글 좋아요 취소")
    public ResponseEntity<?> deleteLikes(@PathVariable(name = "board_idx") int boardIdx, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boardService.deleteLikes(userIdx, boardIdx);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/board/alert/{board_idx}")
    @ApiOperation(value = "게시글 신고" , notes = "param에 게시글 신고 내용")
    public ResponseEntity<?> alertBoard(@PathVariable(name = "board_idx") int boardIdx, @RequestParam(name = "content") String content, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            boolean is = boardService.alertBoard(userIdx,boardIdx,content);
            result.put("data", is); // 같은 신고자가 같은 게시물을 한번만 신고가능
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }
    @GetMapping("/board/{board_idx}")
    @ApiOperation(value = "게시글 상세 조회" , notes = "게시글 세부 내용,댓글 내용들 모두 포함")
    public ResponseEntity<?> InfoBoardComment(@PathVariable(name = "board_idx") int boardIdx, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        try {
            // 글 관련 정보들
            BoardListDto boardData = boardService.infoBoard(boardIdx,userIdx);
            result.put("board",boardData);
            // 댓글 관련 정보들
            List<CommentListDto> commentData = boardService.infoComment(boardIdx,userIdx);
            result.put("comment",commentData);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message", FAIL);
            System.out.println(e);
        }
        return new ResponseEntity<>(result, status);
    }
    @GetMapping("/board/themeList/{theme_idx}")
    public BoardInfoDto boardInfoByTheme(@PathVariable(name = "theme_idx") int themeIdx){ // 해당 테마 번호로 게시물 정보 받기
        return boardService.boardInfoByTheme(themeIdx);
    }
    @GetMapping("/board/themeInfo/{theme_idx}/{user_idx}")
    public BoardInfoForUserDto boardInfoForUser(@PathVariable(name = "theme_idx") int themeIdx,@PathVariable(name = "user_idx") int userIdx){ // 해당 테마 번호로 게시물,댓글,챌린지 정보 받기
        return boardService.boardInfoForUser(themeIdx,userIdx);
    }
    @DeleteMapping("/deleteBoardAndComment/{theme_idx}")
    String deleteBoardAndComment(@PathVariable(name = "theme_idx") int theme_idx){
        try {
            boardService.deleteBoardAndComment(theme_idx);
        } catch (Exception e){
            return FAIL;
        }
        return OK;
    }

}
