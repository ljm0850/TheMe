package com.ssafy.feed.service.impl;

import com.ssafy.feed.entity.Alert;
import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Comment;
import com.ssafy.feed.repository.AlertRepository;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.repository.CommentRepository;
import com.ssafy.feed.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    BoardRepository boardRepository;
    CommentRepository commentRepository;
    AlertRepository alertRepository;
    @Autowired
    CommentServiceImpl(BoardRepository boardRepository, CommentRepository commentRepository, AlertRepository alertRepository){
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.alertRepository = alertRepository;
    }
    @Override
    public boolean registComment(int boardIdx, int userIdx, String content) {
        Optional<Board> board = boardRepository.findById(boardIdx);
        Comment comment = Comment.builder()
                .board(board.get())
                .alertCount(0)
                .content(content)
                .userIdx(userIdx)
                .build();
        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean deleteComment(int commentIdx) {
        commentRepository.deleteById(commentIdx);
        return true;
    }

    @Override
    public boolean alertComment(int userIdx, int commentIdx, String content) {
        // 같은 신고자가 같은 댓글 두번 이상 신고 불가능
        Optional<Alert> isAlert = alertRepository.findByReferenceIdxAndTypeAndReportUserIdx(commentIdx,1,userIdx);
        if(isAlert.isPresent()) return false;
        else {
            Optional<Comment> comment = commentRepository.findById(commentIdx);
            int targetIdx = comment.get().getUserIdx(); // 신고대상
            Alert alert = Alert.builder()
                    .content(content)
                    .createTime(LocalDateTime.now())
                    .type(1) // 댓글 1번
                    .reportUserIdx(userIdx)
                    .targetUserIdx(targetIdx)
                    .referenceIdx(commentIdx)
                    .build();
            alertRepository.save(alert);
            comment.get().updateAlertCount(comment.get().getAlertCount()+1);
            commentRepository.save(comment.get());
            return true;
        }
    }
}
