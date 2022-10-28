package com.ssafy.theme.repository;


import com.ssafy.theme.entity.UserTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThemeRepository extends JpaRepository<UserTheme,Integer> {
}
