package com.ssafy.feed;

import com.ssafy.feed.entity.Board;
import com.ssafy.feed.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Transactional
class BoardApplicationTests {
    BoardRepository boardRepository;
    @Autowired
    void contextLoads(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    @Test
    void 게시물등록() {
        int themeIdx = 1; // 게시글 해당 테마 번호
        String userId = "joe5"; // 게시글 작성자
        String name = "컴포즈"; // 게시글 장소 이름
        String place = "광주광역시 북구"; // 게시글 장소 주소
        String description = "설명이에요"; // 게시글 장소 설명

        System.out.println(place.substring(0,2));

        Board board = Board.builder()
                .alertCount(0)
                .city(place.substring(0,2))
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .name(name)
                .userId(userId)
                .themeIdx(themeIdx)
                .description(description)
                .place(place)
                .build();

        boardRepository.save(board);
    }

    @Test
    void 게시글삭제(){
        String userId = "joe5"; // 게시글 작성자
        int boardIdx = 5;
        // boardIdx로 작성자인지 확인 - 없어도 될듯
        Optional<Board> board = boardRepository.findById(boardIdx);
        if(board.get().getUserId().equals(userId)){
            boardRepository.deleteById(boardIdx);
        }
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
}
