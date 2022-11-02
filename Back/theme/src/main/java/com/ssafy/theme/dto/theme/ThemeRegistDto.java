package com.ssafy.theme.dto.theme;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class ThemeRegistDto {
    String name;
    String emoticon;
    public ThemeRegistDto(String n, String e){
        this.name = n;
        this.emoticon = e;
    }
}
