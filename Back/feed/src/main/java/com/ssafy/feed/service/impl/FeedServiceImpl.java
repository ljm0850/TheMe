package com.ssafy.feed.service.impl;

import com.ssafy.feed.client.ThemeClient;
import com.ssafy.feed.client.UserClient;
import com.ssafy.feed.dto.board.BoardDto;
import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.board.BoardSimpleListDto;
import com.ssafy.feed.dto.theme.UserThemeDtoWithMSA;
import com.ssafy.feed.dto.user.UserInfoByIdDto;
import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Comment;
import com.ssafy.feed.entity.Likes;
import com.ssafy.feed.entity.Picture;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.repository.CommentRepository;
import com.ssafy.feed.repository.LikeRepository;
import com.ssafy.feed.repository.PictureRepository;
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
    CommentRepository commentRepository;
    LikeRepository likeRepository;
    UserClient userClient;
    PictureRepository pictureRepository;
    @Autowired
    FeedServiceImpl(LikeRepository likeRepository,ThemeClient themeClient,PictureRepository pictureRepository,BoardRepository boardRepository,CommentRepository commentRepository,UserClient userClient){
        this.themeClient =themeClient;
        this.likeRepository = likeRepository;
        this.boardRepository=boardRepository;
        this.commentRepository = commentRepository;
        this.userClient = userClient;
        this.pictureRepository = pictureRepository;
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

    @Override
    public List<BoardSimpleListDto> themeBoardList(int theme_idx, String name, int pageIdx, int pageSize,int userIdx) {
        List<UserThemeDtoWithMSA> themeUserList = themeClient.getThemeUserList(theme_idx);
        List<Integer> openUserList = new ArrayList<>();
        List<BoardSimpleListDto> boardSimpleListDtoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageIdx, pageSize);
        for(UserThemeDtoWithMSA theme : themeUserList){
            openUserList.add(theme.getUserIdx());
        }
        List<Board> boardGroupListDto = boardRepository.getBoardListWithJPA(openUserList,theme_idx,name,pageable);

        for(Board board : boardGroupListDto){
            System.out.println(board.getIdx());
            Integer commentCount = commentRepository.findByBoard(board).size();
            List<Likes> likeCount = likeRepository.findByBoard(board);
            System.out.println("1");
            UserInfoByIdDto userInfo = userClient.getUserInfo(board.getUserIdx());
            List<Picture> pictureList = pictureRepository.findByBoard(board);
            String themeName = themeClient.getThemeName(theme_idx);
            boolean isWriter = false;
            System.out.println("2");
            String []  pictures= new String[pictureList.size()];
            for(int i=0;i<pictureList.size();i++){
                pictures[i] = (pictureList.get(i).getPicture());
            }
            if(board.getUserIdx() == userIdx) isWriter = true;
            System.out.println("3");
            BoardSimpleListDto boardSimpleListDto = BoardSimpleListDto.builder()
                    .boardIdx(board.getIdx())
                    .alertCount(board.getAlertCount())
                    .city(board.getCity())
                    .commentCount(commentCount)
                    .isWriter(isWriter)
                    .likeCount(0)
                    .modifyTime(board.getModifyTime())
                    .name(board.getName())
                    .nickname(userInfo.getNickname())
                    .picture(pictures)
                    .themeIdx(board.getThemeIdx())
                    .themeName(themeName)
                    .profile(userInfo.getPicture())
                    .userIdx(userIdx)
                    .build();
            System.out.println(boardGroupListDto.toArray());
            boardSimpleListDtoList.add(boardSimpleListDto);
        }
        return boardSimpleListDtoList;
    }

    @Override
    public List<BoardDto> getUserBoardList(int user_idx) {
        List<Board> userBoardList = boardRepository.findByUserIdx(user_idx);

        List<BoardDto> boardDtos = new ArrayList<>();
        for(int i=0;i<userBoardList.size();i++) {
            Board board = userBoardList.get(i);

            BoardDto boardDto = BoardDto.builder()
                    .createTime(board.getCreateTime())
                    .description(board.getDescription())
                    .idx(board.getIdx())
                    .city(board.getCity())
                    .alertCount(board.getAlertCount())
                    .name(board.getName())
                    .place(board.getPlace())
                    .userIdx(board.getUserIdx())
                    .themeIdx(board.getThemeIdx())
                    .modifyTime(board.getModifyTime())
                    .build();

            boardDtos.add(boardDto);
        }

        return boardDtos;
    }
}
