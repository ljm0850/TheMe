package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.dto.board.BoardUpdateDto;

public interface BoardService {
    void registBoard(String userId, BoardRegistDto boardRegistDto);

    void deleteBoard(int boardIdx);

    boolean updateBoard(int boardIdx, BoardUpdateDto boardUpdateDto);
}
