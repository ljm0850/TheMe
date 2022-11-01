package com.ssafy.feed.service.impl;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.dto.board.BoardUpdateDto;
import com.ssafy.feed.entity.Board;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.service.BoardService;
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
    public void registBoard(String userId, BoardRegistDto boardRegistDto) { // 게시글 등록
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
    public void deleteBoard(int boardIdx) { // 게시글 삭제
        boardRepository.deleteById(boardIdx);
    }

    @Override
    public boolean updateBoard(int boardIdx, BoardUpdateDto boardUpdateDto) { // 게시글 수정
        Optional<Board> board = boardRepository.findById(boardIdx);
        board.get().updateThemeIdx(boardUpdateDto.getThemeIdx());
        board.get().updateDescription(boardUpdateDto.getDescription());
        board.get().updateName(boardUpdateDto.getName());
        board.get().updatePlace(boardUpdateDto.getPlace());
        board.get().updateCity(boardUpdateDto.getPlace().substring(0,2));
        board.get().updateTime(LocalDateTime.now());
        boardRepository.save(board.get());
        return true;
    }

    @Override
    public void likesBoard(String userId, int boardIdx) {

    }
}
