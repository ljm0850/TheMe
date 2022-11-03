package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.board.BoardSimpleListDto;

import java.util.List;

public interface FeedService {
    List<BoardGroupListDto> themeBoardGroup(int theme_idx,int pageIdx,int pageSize);

    List<BoardSimpleListDto> themeBoardList(int theme_idx, String name, int pageIdx, int pageSize,int userIdx);
}
