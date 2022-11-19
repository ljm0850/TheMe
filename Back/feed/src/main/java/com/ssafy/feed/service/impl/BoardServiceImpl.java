package com.ssafy.feed.service.impl;

import com.ssafy.feed.client.ThemeClient;
import com.ssafy.feed.client.UserClient;
import com.ssafy.feed.dto.board.*;
import com.ssafy.feed.dto.comment.CommentListDto;
import com.ssafy.feed.dto.user.UserInfoByIdDto;
import com.ssafy.feed.entity.*;
import com.ssafy.feed.repository.*;
import com.ssafy.feed.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int registBoard(int userIdx, BoardRegistDto boardRegistDto) { // 게시글 등록
        int idx = isUserTheme(userIdx,boardRegistDto.getThemeIdx());
        String[] pictures = boardRegistDto.getPictures(); // 등록할 사진목록
        Board board = Board.builder()
                .alertCount(0)
                .city(checkCity(boardRegistDto.getPlace()))
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .name(boardRegistDto.getName())
                .userIdx(userIdx)
                .themeIdx(idx)
                .description(boardRegistDto.getDescription())
                .place(boardRegistDto.getPlace())
                .latitude(boardRegistDto.getLatitude())
                .longitude(boardRegistDto.getLogitude())
                .build();
        boardRepository.save(board);
        for(int i = 0; i < pictures.length; i++){
            Picture picture = Picture.builder()
                    .picture(pictures[i])
                    .board(board)
                    .build();
            pictureRepository.save(picture);
        }
        return board.getIdx();
    }

    @Override
    public void deleteBoard(int userIdx, int boardIdx) { // 게시글 삭제
        Optional<Board> board = boardRepository.findById(boardIdx);
        if(board.get().getUserIdx()==userIdx){ // 작성자인지 한번더 확인하기
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
    }

    @Override
    public boolean updateBoard(int userIdx, int boardIdx, BoardUpdateDto boardUpdateDto) { // 게시글 수정
        String[] pictures = boardUpdateDto.getPictures();
        Optional<Board> board = boardRepository.findById(boardIdx);
        if(board.get().getUserIdx()==userIdx) { // 작성자인지 한번더 확인하기
            board.get().updateThemeIdx(boardUpdateDto.getThemeIdx());
            board.get().updateDescription(boardUpdateDto.getDescription());
            board.get().updateName(boardUpdateDto.getName());
            board.get().updatePlace(boardUpdateDto.getPlace());
            board.get().updateCity(checkCity(boardUpdateDto.getPlace()));
            board.get().updateTime(LocalDateTime.now());
            board.get().updateLatitude(boardUpdateDto.getLatitude());
            board.get().updateLongitude(boardUpdateDto.getLogitude());
            boardRepository.save(board.get());
            List<Picture> pictureList = pictureRepository.findByBoard(board.get()); // 기존 사진 삭제
            for (int i = 0; i < pictureList.size(); i++) {
                pictureRepository.deleteById(pictureList.get(i).getIdx());
            }
            for (int i = 0; i < pictures.length; i++) { // 수정된 사진 재등록
                Picture picture = Picture.builder()
                        .picture(pictures[i])
                        .board(board.get())
                        .build();
                pictureRepository.save(picture);
            }
            return true;
        }
        else return false;
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
        List<Likes> likes = likeRepository.findByUserIdxAndBoard(userIdx, board.get());
        likeRepository.deleteById(likes.get(0).getIdx());
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
            List<Alert> alertList = alertRepository.findByReferenceIdxAndType(boardIdx,0);
            if(alertList.size()==5) alertUser(targetIdx); // 5회 이상이면 해당 유저 1회 신고
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
                .themeName(getUserThemeName(board.get().getThemeIdx()))
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
    public String getUserThemeName(int themeIdx) { // 테마 idx로 이름 받아오기
        String themeInfo = themeClient.getUserThemeName(themeIdx);
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

    @Override
    public String alertUser(int userIdx){
        String result = userClient.alertUser(userIdx);
        return result;
    }
    @Override
    public BoardInfoDto boardInfoByTheme(int themeIdx){
        List<Board> boardList = boardRepository.findByThemeIdxOrderByModifyTimeDesc(themeIdx);
        List<Picture> pictureList = new ArrayList<>();
        int count = 0;
        for(int i=0;i<boardList.size();i++){
            if(count>5) break; // 5개만 가져오기
            List<Picture> pictures = pictureRepository.findByBoardOrderByIdxDesc(boardList.get(i));
            if(pictures.size()>0) {
                pictureList.add(pictures.get(0)); // 한 사람의 게시물 중 하나의 사진만 나오도록 하기
                count++;
            }
        }
        String[] picturesList = new String[pictureList.size()];
        for(int i=0;i<pictureList.size();i++){
            picturesList[i] = pictureList.get(i).getPicture();
        }
        BoardInfoDto boardInfoDto = BoardInfoDto.builder()
                .boardCount(boardList.size())
                .pictures(picturesList)
                .build();
        return boardInfoDto;
    }

    @Override
    public BoardInfoForUserDto boardInfoForUser(int themeIdx,int userIdx) {
        int commentCount = 0;
        List<Picture> pictureList = new ArrayList<>(); // 대표 사진
        List<Board> boardList = boardRepository.findByThemeIdxOrderByModifyTimeDesc(themeIdx);
        for(int i=0;i<boardList.size();i++){
            List<Comment> comment = commentRepository.findByBoard(boardList.get(i));
            commentCount += comment.size();
        }
        int count = 0;
        for(int i=0;i<boardList.size();i++){
            if(count>5) break; // 5개만 가져오기
            List<Picture> pictures = pictureRepository.findByBoardOrderByIdxDesc(boardList.get(i));
            if(pictures.size()>0) {
                pictureList.add(pictures.get(0)); // 한 사람의 게시물 중 하나의 사진만 나오도록 하기
                count++;
            }
        }
        String[] picturesList = new String[pictureList.size()];
        for(int i=0;i<pictureList.size();i++){
            picturesList[i] = pictureList.get(i).getPicture();
        }
        List<Board> allBoardList = boardRepository.findByThemeIdxGroupByPlace(themeIdx);
        List<Board> currentBoardList = boardRepository.findByThemeIdxAndUserIdxGroupByPlace(themeIdx,userIdx);
        List<Board> personBoardList = boardRepository.findByThemeIdxGroupByUserIdx(themeIdx);
        BoardInfoForUserDto boardInfoForUserDto = BoardInfoForUserDto.builder()
                .commentCount(commentCount)
                .personCount(personBoardList.size())
                .allBoardCount(allBoardList.size()) // 전체 장소 수
                .currentBoardCount(currentBoardList.size()) // 현재 자신이 간 장소 수
                .boardCount(boardList.size()) // 전체 게시물 수
                .pictures(picturesList)
                .build();
        return boardInfoForUserDto;
    }
    @Override
    public int isUserTheme(int userIdx, int themeIdx){
        return themeClient.isUserTheme(userIdx,themeIdx);
    }

    @Override
    @Transactional
    public void deleteBoardAndComment(int theme_idx) {
        List<Board> boardList = boardRepository.findByThemeIdx(theme_idx);
        for(Board board : boardList){
            // 게시글 삭제하기
            boardRepository.delete(board);
            // 댓글 삭제하기
            List<Comment> commentList = commentRepository.findByBoard(board);
            for(Comment comment : commentList){
                commentRepository.delete(comment);
            }
            // 좋아요 삭제하기
            List<Likes> likesList = likeRepository.findByBoard(board);
            for(Likes likes : likesList){
                likeRepository.delete(likes);
            }
            // 사진 삭제하기
            List<Picture> pictureList = pictureRepository.findByBoard(board);
            for(Picture picture : pictureList){
                pictureRepository.delete(picture);
            }

        }
    }
}
