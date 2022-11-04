package com.ssafy.feed.service.impl;

import com.ssafy.feed.client.ThemeClient;
import com.ssafy.feed.client.UserClient;
import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.board.BoardSimpleListDto;
import com.ssafy.feed.dto.theme.UserThemeDtoWithMSA;
import com.ssafy.feed.dto.user.UserFollowThemeDto;
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
import java.util.Optional;

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
    public List<BoardSimpleListDto> feedByRegion(int userIdx, int region, int pageIdx, int pageSize) {
        Pageable pageable = PageRequest.of(pageIdx, pageSize); // 페이지네이션
        String[] regionList = {"전국","서울","대전","광주","구미","부울경"};
        List<BoardSimpleListDto> boardSimpleListDtoList = new ArrayList<>();
        List<UserFollowThemeDto> userFollowThemeDtoList = userClient.getUserFollowTheme(userIdx);
        for(int i=0;i<userFollowThemeDtoList.size();i++){ // 해당 유저가 팔로우한 사람의 테마리스트들 확인
            int followUserIdx = userFollowThemeDtoList.get(i).getFollowUserIdx();
            int followThemeIdx = userFollowThemeDtoList.get(i).getFollowThemeIdx();
            int openType = getThemeOpenType(followUserIdx,followThemeIdx); // 0 전체 공개, 1 친구 공개, 2 비공개
            if(openType!=2){
                // 해당 사람이 해당 테마로 작성한 글들 불러오기
                List<Board> boardList;
                if(region == 0) boardList = boardRepository.findByUserIdxAndThemeIdx(followUserIdx,followThemeIdx);
                else boardList = boardRepository.findByUserIdxAndThemeIdxAndCity(followUserIdx,followThemeIdx,regionList[region],pageable);// 지역별로 나눠서 확인해야함 - 받은 지역 확인하기
                for(int j=0;j<boardList.size();j++) {
                    UserInfoByIdDto userInfo = userClient.getUserInfo(boardList.get(j).getUserIdx()); // 해당 게시글을 작성한 유저의 정보 받기
                    Optional<Board> board = boardRepository.findById(boardList.get(j).getIdx()); // 해당 게시글
                    List<Likes> likeList = likeRepository.findByBoard(board.get()); // 해당 게시글의 좋아요
                    List<Comment> commentList = commentRepository.findByBoard(board.get()); // 해당 게시글의 댓글
                    List<Picture> pictureList = pictureRepository.findByBoard(board.get()); // 해당 게시글의 사진
                    String[] pictures = new String[pictureList.size()];
                    for(int k=0;k<pictureList.size();k++){
                        pictures[k] = (pictureList.get(k).getPicture());
                    }
                    boolean isWriter = true;
                    if(boardList.get(j).getUserIdx()!=userIdx) isWriter = false;
                    BoardSimpleListDto boardSimpleListDto = BoardSimpleListDto.builder()
                            .boardIdx(boardList.get(j).getIdx())
                            .alertCount(boardList.get(j).getAlertCount())
                            .city(boardList.get(j).getCity())
                            .commentCount(commentList.size())
                            .isWriter(isWriter)
                            .likeCount(likeList.size())
                            .modifyTime(boardList.get(j).getModifyTime())
                            .name(boardList.get(j).getName())
                            .nickname(userInfo.getNickname())
                            .picture(pictures)
                            .themeIdx(boardList.get(j).getThemeIdx())
                            .themeName(themeClient.getThemeName(boardList.get(j).getThemeIdx()))
                            .profile(userInfo.getPicture())
                            .userIdx(boardList.get(j).getUserIdx())
                            .build();
                }
            }
        }
        return boardSimpleListDtoList;
    }
    @Override
    public List<UserFollowThemeDto> getUserFollowTheme(int userIdx){ // 해당 유저가 팔로우하고 있는 사람,테마의 idx받아오기
        List<UserFollowThemeDto> userFollowThemeDtoList = userClient.getUserFollowTheme(userIdx);
        return userFollowThemeDtoList;
    }

    @Override
    public int getThemeOpenType(int followUserIdx, int followThemeIdx) { // 해당 테마의 공개 여부 확인하기
        return themeClient.getThemeOpenType(followUserIdx,followThemeIdx);
    }
    @Override
    public String getThemeName(int themeIdx) { // 테마 idx로 이름 받아오기
        String themeInfo = themeClient.getThemeName(themeIdx);
        return themeInfo;
    }
}
