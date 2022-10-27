package com.ssafy.etc.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class FeedRecommendDto {
    String name;
    String emoticon;
    public FeedRecommendDto(String n, String e){
        this.name = n;
        this.emoticon = e;
    }
}
