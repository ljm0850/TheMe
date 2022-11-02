package com.ssafy.feed.controller;

import com.ssafy.feed.dto.board.BoardListDto;
import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.dto.board.BoardUpdateDto;
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
        //int userIdx = (int) request.getAttribute("userIdx");
        int userIdx = 2;
        try {
            boardService.registBoard(userIdx, boardRegistDto);
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
    public ResponseEntity<?> updateBoard(@PathVariable(name = "board_idx") int boardIdx, @RequestBody BoardUpdateDto boardUpdateDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            boolean data = boardService.updateBoard(boardIdx,boardUpdateDto);
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
        //int userIdx = (int) request.getAttribute("userIdx");
        int userIdx = 1;
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
        //int userIdx = (int) request.getAttribute("userIdx");
        int userIdx = 1;
        try {
            boardService.deleteLikes(userIdx, boardIdx);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e);
            result.put("message", FAIL);
        }
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/board/alert/{board_idx}")
    @ApiOperation(value = "게시글 신고" , notes = "param에 게시글 신고 내용")
    public ResponseEntity<?> alertBoard(@PathVariable(name = "board_idx") int boardIdx, @RequestParam(name = "content") String content, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        //int userIdx = (int) request.getAttribute("userIdx");
        int userIdx = 1;
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
        //int userIdx = (int) request.getAttribute("userIdx");
        int userIdx = 5;
        try {
            // 글 관련 정보들
            BoardListDto boardData = boardService.infoBoard(boardIdx,userIdx);
            result.put("board",boardData);
            // 댓글 관련 정보들
//            List<CommentListDto> commentData = boardService.infoComment(boardIdx,userIdx);
//            result.put("comment",commentData);
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
