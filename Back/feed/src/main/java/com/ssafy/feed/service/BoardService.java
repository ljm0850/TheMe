package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardRegistDto;

public interface BoardService {
    void registBoard(String userId, BoardRegistDto boardRegistDto);
}
