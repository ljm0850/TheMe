package com.ssafy.theme.service.impl;

import com.ssafy.theme.dto.theme.ThemeRegistDto;
import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.repository.ThemeRepository;
import com.ssafy.theme.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {
    ThemeRepository themeRepository;
    @Autowired
    ThemeServiceImpl(ThemeRepository themeRepository){
        this.themeRepository = themeRepository;
    }
    @Override
    public void registTheme(ThemeRegistDto themeRegistDto) {

        //builder 사용법
        Theme theme = Theme.builder()
                .name(themeRegistDto.getName())
                .emoticon(themeRegistDto.getEmoticon())
                .createTime(LocalDateTime.now())
                .build();

        themeRepository.save(theme);
    }
}
