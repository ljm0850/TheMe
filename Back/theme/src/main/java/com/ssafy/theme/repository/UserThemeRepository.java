package com.ssafy.theme.repository;


import com.ssafy.theme.dto.theme.PublicThemeDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.entity.UserTheme;
import org.apache.catalina.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserThemeRepository extends JpaRepository<UserTheme,Integer> {
    List<UserTheme> findByUserIdx(int user_idx);
    @Query("SELECT new PublicThemeDto(t.theme.idx, t.theme.name, t.theme.emoticon, t.theme.createTime, count(t.theme.idx)) " +
            "FROM UserTheme t " +
            "group by t.theme.idx order by count(t.theme.idx) desc")
    Slice<PublicThemeDto> getPopularAllThemeListWithJPA( Pageable pageable);
    @Query("SELECT new PublicThemeDto(t.theme.idx, t.theme.name, t.theme.emoticon, t.theme.createTime, count(t.theme.idx)) " +
            "FROM UserTheme t " +
            "group by t.theme.idx order by t.theme.createTime desc")
    Slice<PublicThemeDto> getRecnetAllThemeListWithJPA(Pageable pageable);


    @Query("SELECT U from UserTheme U where U.theme in (Select T FROM Theme T where T.name like CONCAT(:value,'%'))")
    List<UserTheme> searchByName(@Param("value") String value);

    @Query("Select U from UserTheme U where U.theme in (Select T FROM Theme T where T.name =:value)")
    Optional<UserTheme> findByName(@Param("value") String value);

    @Query("SELECT count(t.theme.idx) " +
            "from UserTheme t where t.theme.idx = :idx")
    Long getThemeCountWithJPA(@Param("idx") int idx);

    List<UserTheme> findByTheme(Theme theme);
}
