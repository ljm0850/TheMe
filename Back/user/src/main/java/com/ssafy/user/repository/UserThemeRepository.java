package com.ssafy.user.repository;

import com.ssafy.user.entity.UserTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThemeRepository extends JpaRepository<UserTheme,Integer> {
}
