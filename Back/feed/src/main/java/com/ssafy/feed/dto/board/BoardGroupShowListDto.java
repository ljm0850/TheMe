package com.ssafy.feed.dto.board;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class BoardGroupShowListDto {
    int themeIdx; // 테마 idx
    int userIdx; //유저 idx
    String city; // 테마 이름
    String name; // 장소 이름
    String place; // 장소 주소
    Long boardCount; //게시글 갯수
    String latitude;
    String longitude;
    boolean isMy; // 공용테마에서 보여질때는 isMy,isFollow모두 false로 하고 bookmark여부만 리턴
    boolean isBookMarked; // 개인테마에서 보여질때는 isBookMarked를 false로 하고 isMy,isFollow 여부만 확인하기
    boolean isFollow;
    int boardIdx;

    @Builder
    public BoardGroupShowListDto(int themeIdx,int userIdx, String city, String name, String place, Long boardCount
            ,String latitude, String longitude,boolean isMy,boolean isBookMarked, boolean isFollow, int boardIdx) {
        this.themeIdx = themeIdx;
        this.userIdx = userIdx;
        this.city = city;
        this.name = name;
        this.place = place;
        this.boardCount = boardCount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isMy = isMy;
        this.isBookMarked  = isBookMarked;
        this.isFollow = isFollow;
        this.boardIdx = boardIdx;
    }
}
