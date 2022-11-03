package com.ssafy.user.repository;

import com.ssafy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsById(String kakaoId);
    boolean existsByNickname(String nickname);
    User findById(String kakaoId);
    Optional<User>  findByNickname(String nickname);

    @Query("Select U.nickname from User U where U.nickname like CONCAT(:value, '%')")
    List<String> liveSearchByName(@Param("value") String value);

    @Query("Select U from User U  where U.nickname like CONCAT('%',:target,'%')")
    List<User> searchByTarget(@Param("target") String target);

}
