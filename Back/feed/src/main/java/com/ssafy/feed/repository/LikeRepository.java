package com.ssafy.feed.repository;

import com.ssafy.feed.entity.Board;
import com.ssafy.feed.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Integer> {
    Likes findByUserIdxAndBoard(int userIdx, Board board);
}
