package com.ssafy.feed.repository;

import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findByBoard(Board board);
    List<Picture> findByBoardOrderByIdxDesc(Board board);
}
