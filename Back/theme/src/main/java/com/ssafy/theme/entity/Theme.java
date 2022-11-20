package com.ssafy.theme.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    private String name;
    private LocalDateTime createTime;
    private String emoticon;

    @Builder
    public Theme(int idx, String name, LocalDateTime createTime, String emoticon) {
        this.idx = idx;
        this.name = name;
        this.createTime = createTime;
        this.emoticon = emoticon;
    }
}
