package com.ssafy.feed.service;

public interface CommentService {
    boolean registComment(int boardIdx, int userIdx, String content);
}
