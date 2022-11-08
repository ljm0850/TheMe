package com.ssafy.feed.service;

import com.ssafy.feed.dto.board.BoardInfoDto;
import com.ssafy.feed.dto.board.BoardListDto;
import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.dto.board.BoardUpdateDto;
import com.ssafy.feed.dto.comment.CommentListDto;
import com.ssafy.feed.dto.user.UserInfoByIdDto;

import java.util.List;

public interface BoardService {
    int registBoard(int userIdx, BoardRegistDto boardRegistDto);

    void deleteBoard(int userIdx, int boardIdx);

    boolean updateBoard(int userIdx, int boardIdx, BoardUpdateDto boardUpdateDto);

    void likesBoard(int userIdx, int boardIdx);

    void deleteLikes(int userIdx, int boardIdx);

    boolean alertBoard(int userIdx, int boardIdx, String content);

    BoardListDto infoBoard(int boardIdx, int userIdx);

    List<CommentListDto> infoComment(int boardIdx, int userIdx);

    UserInfoByIdDto getUserInfo(int userIdx);

    String getThemeName(int themeIdx);
    String checkCity(String place); // 장소를 통한 지역 재설정
    String alertUser(int userIdx); // 신고

    BoardInfoDto boardInfoByTheme(int themeIdx);
}
