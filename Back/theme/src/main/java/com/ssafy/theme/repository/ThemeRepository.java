package com.ssafy.theme.repository;

import com.ssafy.theme.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {
    Optional<Theme> findByName(String name);
}
