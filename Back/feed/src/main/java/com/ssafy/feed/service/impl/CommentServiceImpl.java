package com.ssafy.feed.service.impl;

import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Comment;
import com.ssafy.feed.repository.BoardRepository;
import com.ssafy.feed.repository.CommentRepository;
import com.ssafy.feed.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    BoardRepository boardRepository;
    CommentRepository commentRepository;
    @Autowired
    CommentServiceImpl(BoardRepository boardRepository, CommentRepository commentRepository){
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
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
}
