package com.ssafy.feed;

import com.ssafy.feed.entity.Alert;
import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Comment;
import com.ssafy.feed.repository.AlertRepository;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class CommentApplicationTests {
    CommentRepository commentRepository;
    BoardRepository boardRepository;
    AlertRepository alertRepository;
    @Autowired
    void CommentApplicationTests(CommentRepository commentRepository, BoardRepository boardRepository, AlertRepository alertRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.alertRepository = alertRepository;
    }

    @Test
    void 댓글등록(){
        String content = "맛있어보인다";
        int userIdx = 3;
        int boardIdx = 1;

        Optional<Board> board = boardRepository.findById(boardIdx);
        Comment comment = Comment.builder()
                .board(board.get())
                .alertCount(0)
                .content(content)
                .userIdx(userIdx)
                .build();
        commentRepository.save(comment);
        System.out.println(comment.getContent());
    }
    @Test
    void 댓글삭제(){
        int commentIdx = 3;
        commentRepository.deleteById(commentIdx);
    }
    @Test
    void 댓글신고(){
        int commentIdx = 2;
        int reportIdx = 4;
        String content = "광고성 댓글이에요";

        // 같은 신고자가 같은 댓글 두번 이상 신고 불가능
        Optional<Alert> isAlert = alertRepository.findByReferenceIdxAndTypeAndReportUserIdx(commentIdx,1,reportIdx);
        if(isAlert.isPresent()) System.out.println("false");
        else {
            Optional<Comment> comment = commentRepository.findById(commentIdx);
            int targetIdx = comment.get().getUserIdx(); // 신고대상
            Alert alert = Alert.builder()
                    .content(content)
                    .createTime(LocalDateTime.now())
                    .type(1) // 댓글 1번
                    .reportUserIdx(reportIdx)
                    .targetUserIdx(targetIdx)
                    .referenceIdx(commentIdx)
                    .build();
            alertRepository.save(alert);
            comment.get().updateAlertCount(comment.get().getAlertCount()+1);
            commentRepository.save(comment.get());
            System.out.println("true");
        }
    }
}
