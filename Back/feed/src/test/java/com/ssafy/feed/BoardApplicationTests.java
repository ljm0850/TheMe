package com.ssafy.feed;

import com.ssafy.feed.entity.Board;
import com.ssafy.feed.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
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
}
