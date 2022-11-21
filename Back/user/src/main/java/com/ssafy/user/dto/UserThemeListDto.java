package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class UserThemeListDto { // 유저 테마 dto

    private int idx;
    private int userIdx; // 테마 작성자
    private int userThemeIdx; // 해당 유저테마 번호
    private int themeIdx; // 부모테마
    private int openType; // 공개여부 (0비공개,1친구공개,2검색허용)
    private LocalDateTime createTime; // 생성시간
    private LocalDateTime modifyTime; // 수정시간
    private boolean challenge; // 챌린지 여부
    private String description; // 테마 소개
    private String name; // 테마 이름
    private String emoticon; // 테마 이모티콘
    private String[] pictures; // 대표 사진들
    private int personCount; // 참여하는 사람 수
    private int boardCount; // 해당 테마에 올라온 게시물 수
    private int commentCount; // 해당 테마에 올라온 댓글 수
    private boolean follow; // 북마크 여부
    private int allChallengeCount; // 총 챌린지 수
    private int currentChallengeCount; // 현재 참여한 챌린지 수
    private boolean isMy; // 본인인지 아닌지 확인

    private int followIdx;
    @Builder
    public UserThemeListDto(int idx, int userThemeIdx, int userIdx, int themeIdx, int openType, LocalDateTime createTime, LocalDateTime modifyTime,
                            boolean challenge, String description, String name, String emoticon, String[] pictures, int personCount,
                            int boardCount, int commentCount, boolean follow, int allChallengeCount, int currentChallengeCount,
                            boolean isMy, int followIdx) {
        this.idx = idx;
        this.userThemeIdx = userThemeIdx;
        this.userIdx = userIdx;
        this.themeIdx = themeIdx;
        this.openType = openType;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.challenge = challenge;
        this.description = description;
        this.name = name;
        this.emoticon = emoticon;
        this.pictures = pictures;
        this.personCount = personCount;
        this.boardCount = boardCount;
        this.commentCount = commentCount;
        this.follow = follow;
        this.allChallengeCount = allChallengeCount;
        this.currentChallengeCount = currentChallengeCount;
        this.isMy = isMy;
        this.followIdx = followIdx;
    }
}

