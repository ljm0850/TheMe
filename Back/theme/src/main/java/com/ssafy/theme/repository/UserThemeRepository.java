package com.ssafy.theme.repository;


import com.ssafy.theme.entity.UserTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserThemeRepository extends JpaRepository<UserTheme,Integer> {
    List<UserTheme> findByUserIdx(int user_idx);
}
