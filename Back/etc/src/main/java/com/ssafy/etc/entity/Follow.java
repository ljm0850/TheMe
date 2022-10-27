package com.ssafy.etc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User followingUser; // 팔로잉 하는 사용자
    @ManyToOne
    @JoinColumn(name = "follow_user_id")
    private User followUser; // 팔로우 할 사용자
    @ManyToOne
    @JoinColumn(name = "follow_theme_idx")
    private Theme theme; // 팔로우 할 테마

}
