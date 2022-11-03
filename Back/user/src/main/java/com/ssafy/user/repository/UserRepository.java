package com.ssafy.user.repository;

import com.ssafy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsById(String kakaoId);
    boolean existsByNickname(String nickname);
    User findByNickname(String nickname);

    @Query("Select U.nickname from User U where U.nickname like CONCAT(:value, '%')")
    List<String> liveSearchByName(@Param("value") String value);
}
