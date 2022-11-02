package com.ssafy.feed.dto.board;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class BoardListDto {
    int boardIdx; // 게시글 번호
    int themeIdx; // 테마 idx
    int userIdx; // 작성자
    int alertCount;
    String themeName; // 테마 이름
    String nickname; // 작성자 닉네임
    String name; // 장소 이름
    String place;
    String description;
    LocalDateTime modifyTime;
    String profile; // 프로필 사진
    String city; // 지역
    String[] picture; // 게시글 사진
    boolean isWriter; // 작성자 여부
    @Builder
    public BoardListDto(int boardIdx,int themeIdx,int userIdx,int alertCount,String themeName,String nickname,String name,String place,String description,LocalDateTime modifyTime,String city,String profile,String[] picture,boolean isWriter){
        this.boardIdx = boardIdx;
        this.themeIdx = themeIdx;
        this.userIdx = userIdx;
        this.alertCount = alertCount;
        this.themeName = themeName;
        this.nickname = nickname;
        this.name = name;
        this.place = place;
        this.description = description;
        this.modifyTime = modifyTime;
        this.city = city;
        this.profile = profile;
        this.picture = picture;
        this.isWriter = isWriter;
    }
}
