package com.ssafy.feed.repository;

import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {

    @Query("select new BoardGroupListDto(b.themeIdx,b.userIdx,b.city,b.name,b.place,count (b.name)) from Board b group by b.name having b.userIdx In (?1) and b.themeIdx=?2 order by count(b.name)")
    List<BoardGroupListDto> getBoardGourpByListWithJPA(List<Integer> userIdx, int themeIdx);
}
