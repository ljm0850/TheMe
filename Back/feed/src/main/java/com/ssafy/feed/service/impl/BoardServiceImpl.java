package com.ssafy.feed.service.impl;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.entity.Board;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.service.BoardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    BoardRepository boardRepository;
    @Autowired
    BoardServiceImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    @Override
    public void registBoard(String userId, BoardRegistDto boardRegistDto) {

        Board board = Board.builder()
                .alertCount(0)
                .city(boardRegistDto.getPlace().substring(0,2))
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .name(boardRegistDto.getName())
                .userId(userId)
                .themeIdx(boardRegistDto.getThemeIdx())
                .description(boardRegistDto.getDescription())
                .place(boardRegistDto.getPlace())
                .build();

        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(int boardIdx) {
        boardRepository.deleteById(boardIdx);
    }
}
