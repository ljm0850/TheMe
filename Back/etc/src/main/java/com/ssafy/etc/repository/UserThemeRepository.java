package com.ssafy.etc.repository;

import com.ssafy.etc.entity.UserTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThemeRepository extends JpaRepository<UserTheme,Integer> {
}
