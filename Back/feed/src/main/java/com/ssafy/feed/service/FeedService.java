package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardDto;
import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.board.BoardGroupShowListDto;
import com.ssafy.feed.dto.board.BoardSimpleListDto;
import com.ssafy.feed.dto.user.UserFollowThemeDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FeedService {
    List<BoardGroupShowListDto> themeBoardGroup(int user_idx, int theme_idx, int pageIdx, int pageSize);
    List<BoardSimpleListDto> themeBoardList(int theme_idx, String name, int pageIdx, int pageSize,int userIdx);
    List<BoardSimpleListDto> feedByRegion(int userIdx, int region, int pageIdx, int pageSize);
    List<UserFollowThemeDto> getUserFollowTheme(int userIdx);
    int getThemeOpenType(int followUserIdx, int followThemeIdx);
    String getThemeName(int themeIdx);
    List<BoardDto> getUserBoardList(int user_idx);

    List<BoardGroupListDto> userThemeList(int userThemeIdx,int pageIdx,int pageSize,int user_idx);
    int whoUserIdx(int userThemeIdx);
    boolean isScrap(int userIdx, int themeIdx);
}
