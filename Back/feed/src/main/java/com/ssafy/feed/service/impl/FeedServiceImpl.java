package com.ssafy.feed.service.impl;

import com.ssafy.feed.client.ThemeClient;
import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.theme.UserThemeDtoWithMSA;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {
    ThemeClient themeClient;
    BoardRepository boardRepository;
    @Autowired
    FeedServiceImpl(ThemeClient themeClient,BoardRepository boardRepository){
        this.themeClient =themeClient;
        this.boardRepository=boardRepository;
    }
    @Override
    public List<BoardGroupListDto> themeBoardGroup(int theme_idx,int pageIdx,int pageSize) {
        List<UserThemeDtoWithMSA> themeUserList = themeClient.getThemeUserList(theme_idx); //테마 번호로 openType이 1인 userTheme만 받아오기
        List<Integer> openUserList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageIdx, pageSize);
        for(UserThemeDtoWithMSA theme : themeUserList){ //해당 번호 배열로 저장
            openUserList.add(theme.getUserIdx());
        }

        return boardRepository.getBoardGourpByListWithJPA(openUserList,theme_idx,pageable); //게시글 목록 리턴
    }
}
