package com.ssafy.feed;

import com.ssafy.feed.client.ThemeClient;
import com.ssafy.feed.client.UserClient;
import com.ssafy.feed.dto.board.BoardDto;
import com.ssafy.feed.entity.Board;
import com.ssafy.feed.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FeedApplicationTests {

    BoardRepository boardRepository;
    LikeRepository likeRepository;
    AlertRepository alertRepository;
    PictureRepository pictureRepository;
    CommentRepository commentRepository;
    UserClient userClient;
    ThemeClient themeClient;
    @Autowired
    void BoardApplicationTests(ThemeClient themeClient,BoardRepository boardRepository,LikeRepository likeRepository, AlertRepository alertRepository,PictureRepository pictureRepository, CommentRepository commentRepository,UserClient userClient) {
        this.boardRepository = boardRepository;
        this.themeClient = themeClient;
        this.likeRepository = likeRepository;
        this.alertRepository = alertRepository;
        this.pictureRepository = pictureRepository;
        this.commentRepository = commentRepository;
        this.userClient = userClient;
    }
    @Test
    void contextLoads() {
    }

    @Test
    void 특정유저의게시글리스트() {
        List<Board> userBoardList = boardRepository.findByUserIdx(9);

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
            System.out.println(boardDto.toString());
        }
    }
}
