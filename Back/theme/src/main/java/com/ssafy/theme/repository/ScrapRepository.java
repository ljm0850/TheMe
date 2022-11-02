package com.ssafy.theme.repository;


import com.ssafy.theme.dto.theme.PublicThemeDto;
import com.ssafy.theme.entity.Scrap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
    List<Scrap> findByUserIdx(int userIdx);
}
