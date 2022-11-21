package com.ssafy.theme.repository;

import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.entity.UserTheme;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {
    Optional<Theme> findByName(String name);
    Theme findByIdx(int idx);

    Optional<Theme> findTop1ByOrderByIdxDesc();
    @Query("Select T from Theme T  where T.name like CONCAT('%',:target,'%')")
    List<Theme> searchByTarget(@Param("target") String target);

    @Query("Select T.name from Theme T where T.name like CONCAT('%',:value, '%')")
    List<String> liveSearchByName(@Param("value") String value);
}
