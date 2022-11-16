package com.ssafy.feed.dto.board;

import com.ssafy.feed.dto.comment.CommentListDto;
import com.ssafy.feed.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardSimpleListDto {
    int boardIdx; // 게시글 번호
    int themeIdx; // 테마 idx
    int userIdx; // 작성자
    int alertCount;
    String themeName; // 테마 이름
    String nickname; // 작성자 닉네임
    String name; // 장소 이름
    LocalDateTime modifyTime;
    String profile; // 프로필 사진
    String city; // 지역
    String[] picture; // 게시글 사진
    boolean isWriter; // 작성자 여부
    int likeCount;
    int commentCount;

    List<CommentListDto> commentListDtoList;


    boolean likeMy;
    @Builder
    public BoardSimpleListDto(int boardIdx, int themeIdx, int userIdx, int alertCount, String themeName, String nickname, String name, LocalDateTime modifyTime, String profile, String city, String[] picture, boolean isWriter, int likeCount, int commentCount,boolean likeMy, List<CommentListDto> commentListDtoList) {
        this.boardIdx = boardIdx;
        this.themeIdx = themeIdx;
        this.userIdx = userIdx;
        this.alertCount = alertCount;
        this.themeName = themeName;
        this.nickname = nickname;
        this.name = name;
        this.modifyTime = modifyTime;
        this.profile = profile;
        this.city = city;
        this.picture = picture;
        this.isWriter = isWriter;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.likeMy = likeMy;
        this.commentListDtoList = commentListDtoList;
    }
}
