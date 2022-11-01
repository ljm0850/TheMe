package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.dto.board.BoardUpdateDto;

public interface BoardService {
    void registBoard(int userIdx, BoardRegistDto boardRegistDto);

    void deleteBoard(int boardIdx);

    boolean updateBoard(int boardIdx, BoardUpdateDto boardUpdateDto);

    void likesBoard(int userIdx, int boardIdx);

    void deleteLikes(int userIdx, int boardIdx);
}
