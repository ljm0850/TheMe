package com.ssafy.theme.service.impl;

import com.ssafy.theme.client.UserClient;
import com.ssafy.theme.dto.theme.PublicThemeDto;
import com.ssafy.theme.dto.theme.ThemeDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.dto.theme.ThemeRegistDto;
import com.ssafy.theme.dto.theme.UserThemeIdxDto;
import com.ssafy.theme.entity.Scrap;
import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.entity.UserTheme;
import com.ssafy.theme.repository.ScrapRepository;
import com.ssafy.theme.repository.ThemeRepository;
import com.ssafy.theme.repository.UserThemeRepository;
import com.ssafy.theme.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ThemeServiceImpl implements ThemeService {
    ThemeRepository themeRepository;
    UserThemeRepository userThemeRepository;
    ScrapRepository scrapRepository;
    UserClient userClient;
    @Autowired
    ThemeServiceImpl(ThemeRepository themeRepository,
                     UserThemeRepository userThemeRepository,
                     UserClient userClient,
                     ScrapRepository scrapRepository) {
        this.themeRepository = themeRepository;
        this.userThemeRepository = userThemeRepository;
        this.userClient = userClient;
        this.scrapRepository = scrapRepository;
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
    @Override
    public void createUserTheme(UserThemeDto userThemeDto) {
        UserTheme userTheme = UserTheme.builder()
                .theme(userThemeDto.getTheme())
                .userIdx(userThemeDto.getUserIdx())
                .createTime(userThemeDto.getCreateTime())
                .challenge(userThemeDto.isChallenge())
                .description(userThemeDto.getDescription())
                .modifyTime(userThemeDto.getModifyTime())
                .openType(userThemeDto.getOpenType())
                .build();

        userThemeRepository.save(userTheme);
    }
    @Override
    public List<UserThemeDto> getThemeList(int user_id) {
        List<UserThemeDto> result = new ArrayList<>();

        List<UserTheme> themeList = userThemeRepository.findByUserIdx(user_id);

        for(int i=0;i<themeList.size();i++) {
            UserTheme userTheme = themeList.get(i);

            UserThemeDto target = UserThemeDto.builder()
                    .idx(userTheme.getIdx())
                    .theme(userTheme.getTheme())
                    .challenge(userTheme.isChallenge())
                    .description(userTheme.getDescription())
                    .modifyTime(userTheme.getModifyTime())
                    .createTime(userTheme.getCreateTime())
                    .openType(userTheme.getOpenType())
                    .userIdx(userTheme.getUserIdx())
                    .build();

            result.add(target);
        }

        return result;
    }

    @Override
    public ResponseEntity<?> getUserInfo(String nickname) {
        ResponseEntity<?> userInfo = userClient.getUserInfo(nickname);

        return userInfo;
    }

    @Override
    public ResponseEntity<?> getUserIdxInfo(int userIdx) {
        ResponseEntity<?> userInfo = userClient.getUserIdxInfo(userIdx);

        return userInfo;
    }

    @Override
    public List<PublicThemeDto> getPublicThemeList(int sort, int pageSize, int pageIdx) {
        List<PublicThemeDto> resultList = new ArrayList<>();
        Slice<PublicThemeDto> themeList;
        Pageable pageable = PageRequest.of(pageIdx, pageSize);
            if (sort == 0) { // 인기순
                themeList = userThemeRepository.getPopularAllThemeListWithJPA( pageable);
            } else if(sort == 1) {//최신순
                themeList = userThemeRepository.getRecnetAllThemeListWithJPA( pageable);
            }else{
                return null;
            }
        for(PublicThemeDto publicThemeDto : themeList){
            PublicThemeDto addPublicThemeDto = PublicThemeDto.builder()
                    .idx(publicThemeDto.getIdx())
                    .emoticon(publicThemeDto.getEmoticon())
                    .title(publicThemeDto.getTitle())
                    .userCount(publicThemeDto.getUserCount())
                    .createTime(publicThemeDto.getCreateTime())
                    .build();
            resultList.add(addPublicThemeDto);
        }
        return resultList;
    }
    public List<ThemeDto> searchTheme(String target) {
        List<ThemeDto> result = new ArrayList<>();
        List<Theme> targetThemeList = themeRepository.searchByTarget(target);

        for(int i=0;i<targetThemeList.size();i++) {
            Theme theme = targetThemeList.get(i);

            ThemeDto themeDto = ThemeDto.builder()
                    .idx(theme.getIdx())
                    .createTime(theme.getCreateTime())
                    .emoticon(theme.getEmoticon())
                    .name(theme.getName())
                    .build();

            result.add(themeDto);
        }

        return result;
    }

    @Override
    public void scrapTheme(int userIdx, int themeIdx) {
        Theme theme = themeRepository.findByIdx(themeIdx);
        Scrap scrap = Scrap.builder()
                .theme(theme)
                .userIdx(userIdx)
                .build();

        scrapRepository.save(scrap);
    }

    @Override
    public List<UserThemeDto> followThemeList(UserThemeIdxDto userThemeIdxDto) {
        List<UserThemeDto> result = new ArrayList<>();

        List<Integer> userThemeList = userThemeIdxDto.getUserThemeList();
        for(int i=0; i<userThemeList.size();i++) {

            int userThemeIdx = userThemeList.get(i);
            System.out.println("userThemeIdx : " + userThemeIdx);
            UserTheme userTheme = userThemeRepository.findById(userThemeIdx).orElseThrow(IllegalAccessError::new);

            UserThemeDto userThemeDto = UserThemeDto.builder()
                    .theme(userTheme.getTheme())
                    .userIdx(userTheme.getUserIdx())
                    .createTime(userTheme.getCreateTime())
                    .challenge(userTheme.isChallenge())
                    .description(userTheme.getDescription())
                    .modifyTime(userTheme.getModifyTime())
                    .openType(userTheme.getOpenType())
                    .idx(userTheme.getIdx())
                    .build();

            result.add(userThemeDto);
        }
        return result;
    }

    @Override
    public List<PublicThemeDto> getBookmarkThemeList(int userIdx) {
        List<Scrap> scrapList = scrapRepository.findByUserIdx(userIdx);
        List<PublicThemeDto> publicThemeDtoList = new ArrayList<>();
        for(Scrap scrap : scrapList){
            Long themeCount = userThemeRepository.getThemeCountWithJPA(scrap.getTheme().getIdx());
            PublicThemeDto publicThemeDto = PublicThemeDto.builder()
                    .idx(scrap.getIdx())
                    .createTime(scrap.getTheme().getCreateTime())
                    .emoticon(scrap.getTheme().getEmoticon())
                    .title(scrap.getTheme().getName())
                    .userCount(themeCount)
                    .build();
            publicThemeDtoList.add(publicThemeDto);
        }
        return publicThemeDtoList;
    }
}
