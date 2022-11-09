package com.ssafy.feed.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class BoardInfoForUserDto {
    int boardCount; // 총 게시물 수
    int allBoardCount; // 총 장소 수
    int currentBoardCount; // 현재 자신이 관련 테마에 대해 올린 게시물 수
    int commentCount;
    int personCount; // 참여자수
    String[] pictures; // 대표 사진
    @Builder
    public BoardInfoForUserDto(int boardCount, int allBoardCount, int currentBoardCount, int commentCount, int personCount, String[] pictures) {
        this.boardCount = boardCount;
        this.allBoardCount = allBoardCount;
        this.currentBoardCount = currentBoardCount;
        this.commentCount = commentCount;
        this.personCount = personCount;
        this.pictures = pictures;
    }
}
