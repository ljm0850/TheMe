package com.ssafy.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class UserThemeIdxDto {

    private List<Integer> userThemeList;

    @Builder
    public UserThemeIdxDto(List<Integer> userThemeList) {
        this.userThemeList = userThemeList;
    }
}

