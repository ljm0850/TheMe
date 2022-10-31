package com.ssafy.user.repository;

import com.ssafy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsById(String kakaoId);
    boolean existsByNickname(String nickname);
    User findByNickname(String nickname);
}
