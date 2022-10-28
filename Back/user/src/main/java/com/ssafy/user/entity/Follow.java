package com.ssafy.user.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
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

    private int themeIdx; // 팔로우 할 테마

    @Builder
    public Follow(int idx, User followingUser, User followUser, int themeIdx) {
        this.idx = idx;
        this.followingUser = followingUser;
        this.followUser = followUser;
        this.themeIdx = themeIdx;
    }
}
