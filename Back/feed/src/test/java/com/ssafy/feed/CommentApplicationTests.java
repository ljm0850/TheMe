package com.ssafy.feed;

import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Comment;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CommentApplicationTests {
    CommentRepository commentRepository;
    BoardRepository boardRepository;
    @Autowired
    void CommentApplicationTests(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
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
}
