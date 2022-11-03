package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardListDto;
import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.dto.board.BoardUpdateDto;
import com.ssafy.feed.dto.comment.CommentListDto;
import com.ssafy.feed.dto.user.UserInfoByIdDto;

import java.util.List;

public interface BoardService {
    void registBoard(int userIdx, BoardRegistDto boardRegistDto);

    void deleteBoard(int boardIdx);

    boolean updateBoard(int boardIdx, BoardUpdateDto boardUpdateDto);

    void likesBoard(int userIdx, int boardIdx);

    void deleteLikes(int userIdx, int boardIdx);

    boolean alertBoard(int userIdx, int boardIdx, String content);

    BoardListDto infoBoard(int boardIdx, int userIdx);

    List<CommentListDto> infoComment(int boardIdx, int userIdx);

    UserInfoByIdDto getUserInfo(int userIdx);

    String getThemeName(int themeIdx);
}
