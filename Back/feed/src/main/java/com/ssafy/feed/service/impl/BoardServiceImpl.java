package com.ssafy.feed.service.impl;

import com.ssafy.feed.client.ThemeClient;
import com.ssafy.feed.client.UserClient;
import com.ssafy.feed.dto.board.BoardListDto;
import com.ssafy.feed.dto.board.BoardRegistDto;
import com.ssafy.feed.dto.board.BoardUpdateDto;
import com.ssafy.feed.dto.comment.CommentListDto;
import com.ssafy.feed.dto.user.UserInfoByIdDto;
import com.ssafy.feed.entity.*;
import com.ssafy.feed.repository.*;
import com.ssafy.feed.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
    BoardRepository boardRepository;
    LikeRepository likeRepository;
    AlertRepository alertRepository;
    PictureRepository pictureRepository;
    CommentRepository commentRepository;
    UserClient userClient;
    ThemeClient themeClient;
    @Autowired
    BoardServiceImpl(BoardRepository boardRepository, LikeRepository likeRepository, AlertRepository alertRepository, PictureRepository pictureRepository, CommentRepository commentRepository,UserClient userClient,ThemeClient themeClient){
        this.boardRepository = boardRepository;
        this.likeRepository = likeRepository;
        this.alertRepository = alertRepository;
        this.pictureRepository = pictureRepository;
        this.commentRepository = commentRepository;
        this.userClient = userClient;
        this.themeClient = themeClient;
    }
    @Override
    public void registBoard(int userIdx, BoardRegistDto boardRegistDto) { // 게시글 등록
        String[] pictures = boardRegistDto.getPictures(); // 등록할 사진목록
        Board board = Board.builder()
                .alertCount(0)
                .city(checkCity(boardRegistDto.getPlace()))
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .name(boardRegistDto.getName())
                .userIdx(userIdx)
                .themeIdx(boardRegistDto.getThemeIdx())
                .description(boardRegistDto.getDescription())
                .place(boardRegistDto.getPlace())
                .build();
        boardRepository.save(board);
        for(int i = 0; i < pictures.length; i++){
            Picture picture = Picture.builder()
                    .picture(pictures[i])
                    .board(board)
                    .build();
            pictureRepository.save(picture);
        }
    }

    @Override
    public void deleteBoard(int boardIdx) { // 게시글 삭제
        Optional<Board> board = boardRepository.findById(boardIdx);
        List<Alert> alertList = alertRepository.findByReferenceIdxAndType(boardIdx, 0); // 게시글 신고 삭제
        for(int i=0;i<alertList.size();i++){
            alertRepository.deleteById(alertList.get(i).getIdx());
        }
        List<Picture> pictureList = pictureRepository.findByBoard(board.get()); // 게시글 사진 삭제
        for(int i=0;i<pictureList.size();i++){
            pictureRepository.deleteById(pictureList.get(i).getIdx());
        }
        List<Comment> commentList = commentRepository.findByBoard(board.get()); // 댓글 삭제
        for(int i=0;i<commentList.size();i++){
            commentRepository.deleteById(commentList.get(i).getIdx());
        }
        boardRepository.deleteById(boardIdx);
    }

    @Override
    public boolean updateBoard(int boardIdx, BoardUpdateDto boardUpdateDto) { // 게시글 수정
        String[] pictures = boardUpdateDto.getPictures();
        Optional<Board> board = boardRepository.findById(boardIdx);
        board.get().updateThemeIdx(boardUpdateDto.getThemeIdx());
        board.get().updateDescription(boardUpdateDto.getDescription());
        board.get().updateName(boardUpdateDto.getName());
        board.get().updatePlace(boardUpdateDto.getPlace());
        board.get().updateCity(checkCity(boardUpdateDto.getPlace()));
        board.get().updateTime(LocalDateTime.now());
        boardRepository.save(board.get());
        List<Picture> pictureList = pictureRepository.findByBoard(board.get()); // 기존 사진 삭제
        for(int i=0;i<pictureList.size();i++){
            pictureRepository.deleteById(pictureList.get(i).getIdx());
        }
        for(int i = 0; i < pictures.length; i++){ // 수정된 사진 재등록
            Picture picture = Picture.builder()
                    .picture(pictures[i])
                    .board(board.get())
                    .build();
            pictureRepository.save(picture);
        }
        return true;
    }

    @Override
    public void likesBoard(int userIdx, int boardIdx) {
        Optional<Board> likeboard = boardRepository.findById(boardIdx);
        Likes likes = Likes.builder()
                .userIdx(userIdx)
                .board(likeboard.get())
                .build();
        likeRepository.save(likes);
    }

    @Override
    public void deleteLikes(int userIdx, int boardIdx) {
        Optional<Board> board = boardRepository.findById(boardIdx);
        Likes likes = likeRepository.findByUserIdxAndBoard(userIdx, board.get());
        likeRepository.deleteById(likes.getIdx());
    }

    @Override
    public boolean alertBoard(int userIdx, int boardIdx, String content) {
        // 같은 신고자가 같은 게시물 두번 이상 신고 불가능
        Optional<Alert> isAlert = alertRepository.findByReferenceIdxAndTypeAndReportUserIdx(boardIdx,0,userIdx);
        if(isAlert.isPresent()) return false;
        else {
            Optional<Board> board = boardRepository.findById(boardIdx);
            int targetIdx = board.get().getUserIdx(); // 신고대상
            Alert alert = Alert.builder()
                    .content(content)
                    .createTime(LocalDateTime.now())
                    .type(0) // 게시글 0번
                    .reportUserIdx(userIdx)
                    .targetUserIdx(targetIdx)
                    .referenceIdx(boardIdx)
                    .build();
            alertRepository.save(alert);
            board.get().updateAlertCount(board.get().getAlertCount()+1);
            boardRepository.save(board.get());
            return true;
        }
    }

    @Override
    public BoardListDto infoBoard(int boardIdx, int userIdx) {
        boolean isWriter = true;
        Map<Object,Object> map = new HashMap<>();
        Optional<Board> board = boardRepository.findById(boardIdx);
        UserInfoByIdDto userInfoByIdDto = userClient.getUserInfo(userIdx);
        List<Picture> pictureList = pictureRepository.findByBoard(board.get());
        String[] pictures = new String[pictureList.size()];
        for(int i=0;i<pictureList.size();i++){
            pictures[i] = (pictureList.get(i).getPicture());
        }
        if(board.get().getUserIdx()!=userIdx) isWriter = false;
        BoardListDto boardListDto = BoardListDto.builder()
                .boardIdx(board.get().getIdx())
                .alertCount(board.get().getAlertCount())
                .city(board.get().getCity())
                .description(board.get().getDescription())
                .name(board.get().getName())
                .isWriter(isWriter)
                .modifyTime(board.get().getModifyTime())
                .place(board.get().getPlace())
                .profile(userInfoByIdDto.getPicture())
                .nickname(userInfoByIdDto.getNickname())
                .picture(pictures)
                .themeIdx(board.get().getThemeIdx())
                .userIdx(board.get().getUserIdx())
                .themeName(getThemeName(board.get().getThemeIdx()))
                .build();
        return boardListDto;
    }

    @Override
    public List<CommentListDto> infoComment(int boardIdx, int userIdx) {
        boolean isWriter = true;
        Optional<Board> board = boardRepository.findById(boardIdx); // 해당 게시글
        List<CommentListDto> commentListDtoList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findByBoard(board.get()); // 해당 게시글 댓글 리스트
        for(int i=0;i<commentList.size();i++){
            UserInfoByIdDto userInfoByIdDto = userClient.getUserInfo(commentList.get(i).getUserIdx());
            if(commentList.get(i).getUserIdx()!=userIdx) isWriter = false;
            else isWriter = true;
            CommentListDto commentListDto = CommentListDto.builder()
                    .commentIdx(commentList.get(i).getIdx())
                    .alertCount(commentList.get(i).getAlertCount())
                    .content(commentList.get(i).getContent())
                    .userIdx(commentList.get(i).getUserIdx())
                    .isWriter(isWriter)
                    .profile(userInfoByIdDto.getPicture())
                    .boardIdx(boardIdx)
                    .nickname(userInfoByIdDto.getNickname())
                    .build();
            commentListDtoList.add(commentListDto);
        }
        return commentListDtoList;
    }

    @Override
    public UserInfoByIdDto getUserInfo(int userIdx) { // 유저정보 받아오기
        UserInfoByIdDto userInfo = userClient.getUserInfo(userIdx);
        return userInfo;
    }
    @Override
    public String getThemeName(int themeIdx) { // 테마 idx로 이름 받아오기
        String themeInfo = themeClient.getThemeName(themeIdx);
        return themeInfo;
    }

    @Override
    public String checkCity(String place) {
        String city = place.substring(0,2);
        List<String> cities = new ArrayList<>(){
            {
                add("서울");
                add("대전");
                add("광주");
                add("구미");
                add("부울경");
            }
        };
        if(!cities.contains(city)){
            if(city.equals("부산") || city.equals("울산")) city = "부울경";
            else if(city.equals("경상")) {
                if(place.substring(2,4).equals("남도")) city = "부울경";
                else if(place.substring(5,7).equals("구미")) city = "구미";
                else city = "전국";
            }
            else city = "전국";
        }
        return city;
    }
}
