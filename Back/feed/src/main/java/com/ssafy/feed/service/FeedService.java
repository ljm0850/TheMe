package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardGroupListDto;

import java.util.List;

public interface FeedService {
    List<BoardGroupListDto> themeBoardGroup(int theme_idx,int pageIdx,int pageSize);
}
