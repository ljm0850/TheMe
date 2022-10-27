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
        Theme theme = new Theme();
        theme.setName(themeRegistDto.getName());
        theme.setEmoticon(themeRegistDto.getEmoticon());
        theme.setCreateTime(LocalDateTime.now());
        themeRepository.save(theme);
    }
}
