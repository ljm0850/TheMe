package com.ssafy.feed.dto.comment;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class CommentListDto {
    int commentIdx; // 댓글 번호
    int boardIdx; // 해당 글 번호
    int userIdx; // 댓글 작성자
    int alertCount; // 신고 횟수
    String nickname; // 작성자 닉네임
    String content; // 댓글 내용
    boolean isWriter; // 작성자 여부
    @Builder
    public CommentListDto(int commentIdx,int boardIdx,int userIdx,int alertCount,String nickname,String content,boolean isWriter){
        this.commentIdx= commentIdx;
        this.boardIdx = boardIdx;
        this.userIdx = userIdx;
        this.alertCount = alertCount;
        this.nickname = nickname;
        this.content = content;
        this.isWriter = isWriter;
    }
}
