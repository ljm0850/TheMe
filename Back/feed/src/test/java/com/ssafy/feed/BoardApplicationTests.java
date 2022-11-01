package com.ssafy.feed;

import com.ssafy.feed.entity.Alert;
import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Likes;
import com.ssafy.feed.entity.Picture;
import com.ssafy.feed.repository.AlertRepository;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.repository.LikeRepository;
import com.ssafy.feed.repository.PictureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class BoardApplicationTests {
    BoardRepository boardRepository;
    LikeRepository likeRepository;
    AlertRepository alertRepository;
    PictureRepository pictureRepository;
    @Autowired
    void BoardApplicationTests(BoardRepository boardRepository,LikeRepository likeRepository, AlertRepository alertRepository,PictureRepository pictureRepository) {
        this.boardRepository = boardRepository;
        this.likeRepository = likeRepository;
        this.alertRepository = alertRepository;
        this.pictureRepository = pictureRepository;
    }
    @Test
    void 게시물등록() {
        int themeIdx = 1; // 게시글 해당 테마 번호
        int userIdx = 1; // 게시글 작성자
        String name = "컴포즈"; // 게시글 장소 이름
        String place = "광주광역시 북구"; // 게시글 장소 주소
        String description = "설명이에요"; // 게시글 장소 설명
        String[] pictures = {"사진1","사진2"};

        System.out.println(place.substring(0,2));

        Board board = Board.builder()
                .alertCount(0)
                .city(place.substring(0,2))
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .name(name)
                .userIdx(userIdx)
                .themeIdx(themeIdx)
                .description(description)
                .place(place)
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

    @Test
    void 게시글삭제(){
        int boardIdx = 5;

        boardRepository.deleteById(boardIdx);
    }

    @Test
    void 게시글수정(){
        int boardIdx = 7;
        int themeIdx = 4; // 게시글 해당 테마 번호
        String name = "컴포즈 수정"; // 게시글 장소 이름
        String place = "광주광역시 북구 수정동"; // 게시글 장소 주소
        String description = "설명이에요 수정수정"; // 게시글 장소 설명
        Optional<Board> board = boardRepository.findById(boardIdx);
        System.out.println(board.get().getPlace());
        board.get().updateThemeIdx(themeIdx);
        board.get().updateDescription(description);
        board.get().updateName(name);
        board.get().updatePlace(place);
        boardRepository.save(board.get());
        System.out.println(board.get().getPlace());
        System.out.println(board.get().getThemeIdx());
    }

    @Test
    void 게시글좋아요(){
        int userIdx = 1;
        int boardIdx = 1;
        Optional<Board> likeboard = boardRepository.findById(boardIdx);
        System.out.println(likeboard.get().toString());
        Likes likes = Likes.builder()
                .userIdx(userIdx)
                .board(likeboard.get())
                .build();
        likeRepository.save(likes);
        System.out.println(likes.getBoard().getPlace());
        System.out.println(likes.getUserIdx());
    }
    @Test
    void 게시글좋아요취소(){
        int userIdx = 1;
        int boardIdx = 1;
        Optional<Board> board = boardRepository.findById(boardIdx);
        Likes likes = likeRepository.findByUserIdxAndBoard(userIdx, board.get());
        likeRepository.deleteById(likes.getIdx());
    }
    @Test
    void 게시글신고(){
        int targetIdx = 0;
        int boardIdx = 1;
        int reportIdx = 2; // 신고자
        String content = "광고성 글이에요";

        // 같은 신고자가 같은 게시물 두번 이상 신고 불가능
        Optional<Alert> isAlert = alertRepository.findByReferenceIdxAndTypeAndReportUserIdx(boardIdx,0,reportIdx);
        if(isAlert.isPresent()) System.out.println("false");
        else {
            Optional<Board> board = boardRepository.findById(boardIdx);
            targetIdx = board.get().getUserIdx(); // 신고대상
            Alert alert = Alert.builder()
                    .content(content)
                    .createTime(LocalDateTime.now())
                    .type(0) // 게시글 0번
                    .reportUserIdx(reportIdx)
                    .targetUserIdx(targetIdx)
                    .referenceIdx(boardIdx)
                    .build();
            alertRepository.save(alert);
            board.get().updateAlertCount(board.get().getAlertCount()+1);
            boardRepository.save(board.get());
            System.out.println("true");
        }
    }
}
