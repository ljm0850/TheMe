package com.ssafy.user.repository;

import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    @Query("Select F.themeIdx From Follow F where F.followingUser =:user")
    List<Integer> findThemeIdByFollowingUser(@Param("user") User user);

    @Query("Select distinct(F.followingUser.idx) From Follow F where F.followUser =:user")
    List<Integer> findFollowerByUser(@Param("user") User user);

    @Query("Select distinct (F.followUser.idx) From Follow F where F.followingUser=:user")
    List<Integer> findFollowingByUser(@Param("user") User user);

    List<Follow> findByFollowUserAndFollowingUser(User FollowerUser, User FollowingUser);

    List<Follow> findByFollowUserOrFollowingUser(User FollowerUser, User FollowingUser);


    @Query("Select F.followUser From Follow F group by F.followUser order by (count(F.followUser)) desc")
    List<User> searchRecommned();

    List<Follow> findByFollowingUser(User user);

    @Query("select F.themeIdx from Follow F group by F.themeIdx order by count(F.themeIdx) desc")
    List<Integer> countByThemeIdx();

}
