package com.ssafy.feed.repository;

import com.ssafy.feed.dto.board.BoardGroupListDto;
import com.ssafy.feed.dto.board.BoardListDto;
import com.ssafy.feed.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {

    @Query("select new BoardGroupListDto(b.themeIdx,b.userIdx,b.city,b.name,b.place,count (b.name)) from Board b group by b.name having b.userIdx In (?1) and b.themeIdx=?2 order by count(b.name) desc")
    List<BoardGroupListDto> getBoardGourpByListWithJPA(List<Integer> openUserList, int themeIdx, Pageable pageable);
    @Query("select b from Board b where b.userIdx In (?1) and b.themeIdx=?2 and b.name=?3 order by b.modifyTime desc")
    List<Board> getBoardListWithJPA(List<Integer> openUserList, int themeIdx, String name, Pageable pageable);
    List<Board> findByUserIdxAndThemeIdx(int userIdx, int themeIdx);
    List<Board> findByUserIdxAndThemeIdxAndCity(int userIdx, int themeIdx, String city, Pageable pageable);
    List<Board> findByUserIdx(int user_idx);
    List<Board> findByThemeIdxOrderByModifyTimeDesc(int themeIdx);
    @Query("select b from Board b group by b.place having b.themeIdx=?1")
    List<Board> findByThemeIdxGroupByPlace(int themeIdx);
    @Query("select b from Board b group by b.place having b.themeIdx=?1 and b.userIdx=?2")
    List<Board> findByThemeIdxAndUserIdxGroupByPlace(int themeIdx,int userIdx);
    @Query("select b from Board b group by b.userIdx having b.themeIdx=?1")
    List<Board> findByThemeIdxGroupByUserIdx(int themeIdx);
}
