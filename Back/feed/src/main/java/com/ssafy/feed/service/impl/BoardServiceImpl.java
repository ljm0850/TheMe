package com.ssafy.feed.service.impl;

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
    @Autowired
    BoardServiceImpl(BoardRepository boardRepository, LikeRepository likeRepository, AlertRepository alertRepository, PictureRepository pictureRepository, CommentRepository commentRepository,UserClient userClient){
        this.boardRepository = boardRepository;
        this.likeRepository = likeRepository;
        this.alertRepository = alertRepository;
        this.pictureRepository = pictureRepository;
        this.commentRepository = commentRepository;
        this.userClient = userClient;
    }
    @Override
    public void registBoard(int userIdx, BoardRegistDto boardRegistDto) { // 게시글 등록
        String[] pictures = boardRegistDto.getPictures(); // 등록할 사진목록
        Board board = Board.builder()
                .alertCount(0)
                .city(boardRegistDto.getPlace().substring(0,2))
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
        board.get().updateCity(boardUpdateDto.getPlace().substring(0,2));
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
                .userIdx(board.get().getUserIdx()) // 테마이름만 받아와서 추가하기
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

}
