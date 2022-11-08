package com.ssafy.theme.service.impl;

import com.ssafy.theme.client.FeedClient;
import com.ssafy.theme.client.UserClient;
import com.ssafy.theme.dto.theme.*;
import com.ssafy.theme.entity.Scrap;
import com.ssafy.theme.entity.Theme;
import com.ssafy.theme.entity.UserTheme;
import com.ssafy.theme.repository.ScrapRepository;
import com.ssafy.theme.repository.ThemeRepository;
import com.ssafy.theme.repository.UserThemeRepository;
import com.ssafy.theme.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class ThemeServiceImpl implements ThemeService {
    ThemeRepository themeRepository;
    UserThemeRepository userThemeRepository;
    ScrapRepository scrapRepository;
    UserClient userClient;
    FeedClient feedClient;
    @Autowired
    ThemeServiceImpl(ThemeRepository themeRepository,
                     UserThemeRepository userThemeRepository,
                     UserClient userClient,
                     ScrapRepository scrapRepository,
                     FeedClient feedClient) {
        this.themeRepository = themeRepository;
        this.userThemeRepository = userThemeRepository;
        this.userClient = userClient;
        this.scrapRepository = scrapRepository;
        this.feedClient = feedClient;
    }
    @Override
    public int registTheme(ThemeRegistDto themeRegistDto,int userIdx) {
        //builder 사용법
        Theme theme = Theme.builder()
                .name(themeRegistDto.getName())
                .emoticon(themeRegistDto.getEmoticon())
                .createTime(LocalDateTime.now())
                .build();
        themeRepository.save(theme);
        // 내가 만든 테마이므로 유저테마에도 등록
        UserTheme userTheme = UserTheme.builder()
                .userIdx(userIdx)
                .challenge(false)
                .createTime(theme.getCreateTime())
                .modifyTime(theme.getCreateTime())
                .theme(theme)
                .build();
        userThemeRepository.save(userTheme);
        return theme.getIdx();
    }
    @Override
    public int createUserTheme(int userIdx, UserThemeRegistDto userThemeRegistDto) {
        Theme theme = themeRepository.findByIdx(userThemeRegistDto.getThemeIdx());
        UserTheme userTheme = UserTheme.builder()
                .theme(theme)
                .userIdx(userIdx)
                .createTime(userThemeRegistDto.getCreateTime())
                .challenge(userThemeRegistDto.isChallenge())
                .description(userThemeRegistDto.getDescription())
                .modifyTime(userThemeRegistDto.getModifyTime())
                .openType(userThemeRegistDto.getOpenType())
                .build();
        UserTheme save = userThemeRepository.save(userTheme);
        return save.getIdx();
    }
    @Override
    public List<UserThemeDto> getThemeList(int user_id) {
        List<UserThemeDto> result = new ArrayList<>();

        List<UserTheme> themeList = userThemeRepository.findByUserIdx(user_id);

        for(int i=0;i<themeList.size();i++) {
            UserTheme userTheme = themeList.get(i);

            UserThemeDto target = UserThemeDto.builder()
                    .idx(userTheme.getIdx())
                    .themeIdx(userTheme.getTheme().getIdx())
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
    public List<SearchThemeDto> searchTheme(String target, int userIdx) {
        List<SearchThemeDto> result = new ArrayList<>();

        //해당 단어를 가진 테마들
        List<Theme> targetThemeList = themeRepository.searchByTarget(target);

        //해당 유저가 가진 유저테마들
        List<UserTheme> userThemeList = userThemeRepository.findByUserIdx(userIdx);
        Map<String, Integer> map = new HashMap<>();

        for(int i=0;i<userThemeList.size();i++)
            map.put(userThemeList.get(i).getTheme().getName(), userThemeList.get(i).getOpenType());

        for(int i=0;i<targetThemeList.size();i++) {
            Theme theme = targetThemeList.get(i);

            //openType 0/1/2에 1씩 더해서 1/2/3 배분 후 없는거는 다시 for문
            if(map.get(theme.getName()) != null) {
                SearchThemeDto searchThemeDto =  SearchThemeDto.builder()
                        .themeIdx(theme.getIdx())
                        .createTime(theme.getCreateTime())
                        .name(theme.getName())
                        .emoticon(theme.getEmoticon())
                        .openType(map.get(theme.getName())+1)
                        .build();

                result.add(searchThemeDto);
            }
        }

        for(int i=0;i<targetThemeList.size();i++) {
            Theme theme = targetThemeList.get(i);

            //openType 0/1/2에 1씩 더해서 1/2/3 배분 후 없는거는 다시 for문
            if(map.get(theme.getName()) == null) {
                SearchThemeDto searchThemeDto =  SearchThemeDto.builder()
                        .themeIdx(theme.getIdx())
                        .createTime(theme.getCreateTime())
                        .name(theme.getName())
                        .emoticon(theme.getEmoticon())
                        .openType(0)
                        .build();

                result.add(searchThemeDto);
            }
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
    public void deleteScrapTheme(int user_id, int theme_idx) {
        Theme targetTheme = themeRepository.findByIdx(theme_idx);
        if(scrapRepository.existsByThemeAndUserIdx(targetTheme, user_id)) {
            Scrap scrap = scrapRepository.findByThemeAndUserIdx(targetTheme, user_id).orElseThrow(IllegalAccessError::new);

            scrapRepository.delete(scrap);
        }
    }

    @Override
    public List<UserThemeDto> followThemeList(UserThemeIdxDto userThemeIdxDto) {
        List<UserThemeDto> result = new ArrayList<>();

        List<Integer> userThemeList = userThemeIdxDto.getUserThemeList();
        for(int i=0; i<userThemeList.size();i++) {

            int userThemeIdx = userThemeList.get(i);
            // System.out.println("userThemeIdx : " + userThemeIdx);
            UserTheme userTheme = userThemeRepository.findById(userThemeIdx).orElseThrow(IllegalAccessError::new);

            UserThemeDto userThemeDto = UserThemeDto.builder()
                    .themeIdx(userTheme.getTheme().getIdx())
                    .name(userTheme.getTheme().getName())
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
    public List<String> liveSearchTheme(String value) {
        List<String> strings = themeRepository.liveSearchByName(value);
        for(int i=0;i<strings.size();i++) {
            System.out.println(strings.get(i));
        }
        return strings;
    }
    @Override
    public String getThemeName(int theme_idx) {
        Theme theme = themeRepository.findByIdx(theme_idx);
        return theme.getName();
    }

    @Override
    public Map<String, Object> searchThemeInfo(String value,int userIdx) {
        Map<String, Object> answer = new HashMap<>();
        List<ThemeListDto> result = new ArrayList<>();
        boolean same = themeRepository.findByName(value).isPresent();
        int sameThemeIdx = 0;
        if(same) { // 아예 같은 값
            Theme theme = themeRepository.findByName(value).orElseThrow(IllegalAccessError::new);
            sameThemeIdx = theme.getIdx();
            BoardInfoDto boardInfoDto = boardInfoByTheme(theme.getIdx());
            Optional<Scrap> scrap = scrapRepository.findByThemeAndUserIdx(theme,userIdx); // 보는 사람이 그 테마를 스크랩 했는지
            boolean flag = false;
            if(scrap.isPresent()) flag = true;
            List<UserTheme> userThemes = userThemeRepository.findByTheme(theme); // 총 몇명이 테마를 참여하는지
            ThemeListDto themeListDto = ThemeListDto.builder()
                    .boardCount(boardInfoDto.getBoardCount())
                    .isBookMarked(flag)
                    .createTime(theme.getCreateTime())
                    .personCount(userThemes.size())
                    .pictures(boardInfoDto.getPictures())
                    .emoticon(theme.getEmoticon())
                    .name(theme.getName())
                    .build();
            result.add(themeListDto);
        }
        List<Theme> themes = themeRepository.searchByTarget(value);
        for(int i=0;i<themes.size();i++) {
                BoardInfoDto boardInfoDto = boardInfoByTheme(themes.get(i).getIdx());
                if(sameThemeIdx!=themes.get(i).getIdx()) { // 위에서 겹치는 거 제외하기
                    Optional<Scrap> scrap = scrapRepository.findByThemeAndUserIdx(themes.get(i), userIdx); // 보는 사람이 그 테마를 스크랩 했는지
                    boolean flag = false;
                    if (scrap.isPresent()) flag = true;
                    List<UserTheme> userThemes = userThemeRepository.findByTheme(themes.get(i)); // 총 몇명이 테마를 참여하는지
                    ThemeListDto themeListDto = ThemeListDto.builder()
                            .boardCount(boardInfoDto.getBoardCount())
                            .isBookMarked(flag)
                            .createTime(themes.get(i).getCreateTime())
                            .personCount(userThemes.size())
                            .pictures(boardInfoDto.getPictures())
                            .emoticon(themes.get(i).getEmoticon())
                            .name(themes.get(i).getName())
                            .build();
                    result.add(themeListDto);
                }
        }
        answer.put("result",result);
        answer.put("isSame", same);
        return answer;
    }

    @Override
    public List<UserThemeDtoWithMSA> getThemeUserList(int theme_idx) {
        Theme theme = themeRepository.findByIdx(theme_idx);
        List<UserTheme> userThemeList = userThemeRepository.findByTheme(theme);
        List<UserThemeDtoWithMSA> userThemeDtoList = new ArrayList<>();
        for(UserTheme userTheme : userThemeList){
            if(userTheme.getOpenType()==1){
                UserThemeDtoWithMSA userThemeDto = UserThemeDtoWithMSA.builder()
                        .idx(userTheme.getTheme().getIdx())
                        .userIdx(userTheme.getUserIdx())
                        .themeEmoticon(userTheme.getTheme().getEmoticon())
                        .themeTitle(userTheme.getTheme().getName())
                        .description(userTheme.getDescription())
                        .openType(userTheme.getOpenType())
                        .createTime(userTheme.getCreateTime())
                        .modifyTime(userTheme.getModifyTime())
                        .build();
                userThemeDtoList.add(userThemeDto);
            }
        }
        return userThemeDtoList;
    }

    @Override
    public List<RecommendDto> getRecommendThemeList() {
        List<RecommendDto> result = new ArrayList<>();

        List<Integer> recommendList = userClient.getRecommendThemeList();
        System.out.println("여기" + recommendList.size());
        for(int i=0;i<recommendList.size();i++) {
            System.out.println(recommendList.get(i));
        }
        for(int i=0;i<recommendList.size();i++) {
            UserTheme userTheme = userThemeRepository.findById(recommendList.get(i)).orElseThrow(IllegalAccessError::new);

            RecommendDto userThemeDto = RecommendDto.builder()
                    .name(userTheme.getTheme().getName())
                    .emoticon(userTheme.getTheme().getEmoticon())
                    .themeIdx(userTheme.getTheme().getIdx())
                    .userIdx(userTheme.getUserIdx())
                    .openType(userTheme.getOpenType())
                    .createTime(userTheme.getCreateTime())
                    .description(userTheme.getDescription())
                    .modifyTime(userTheme.getModifyTime())
                    .challenge(userTheme.isChallenge())
                    .idx(userTheme.getIdx())
                    .build();

            result.add(userThemeDto);

        }

        return result;
    }

    @Override
    public List<UserThemeDto> getUserThemeByUserIdx(int user_idx) {
        List<UserTheme> userThemeList = userThemeRepository.findByUserIdx(user_idx);

        List<UserThemeDto> result = new ArrayList<>();

        for(int i=0;i<userThemeList.size();i++) {
            UserTheme targetUserTheme = userThemeList.get(i);
            UserThemeDto userThemeDto = UserThemeDto.builder()
                    .name(targetUserTheme.getTheme().getName())
                    .emoticon(targetUserTheme.getTheme().getEmoticon())
                    .idx(targetUserTheme.getIdx())
                    .userIdx(targetUserTheme.getUserIdx())
                    .themeIdx(targetUserTheme.getTheme().getIdx())
                    .modifyTime(targetUserTheme.getModifyTime())
                    .createTime(targetUserTheme.getCreateTime())
                    .challenge(targetUserTheme.isChallenge())
                    .openType(targetUserTheme.getOpenType())
                    .description(targetUserTheme.getDescription())
                    .build();

            result.add(userThemeDto);
        }

        return result;
    }

    @Override
    public int getThemeOpenType(int followUserIdx, int followThemeIdx) {
        Theme theme = themeRepository.findByIdx(followThemeIdx);
        Optional<UserTheme> userTheme = userThemeRepository.findByThemeAndUserIdx(theme,followUserIdx); // 테마의 공개 여부
        return userTheme.get().getOpenType();
    }
    @Override
    public BoardInfoDto boardInfoByTheme(int themeIdx) {
        BoardInfoDto boardInfoDto = feedClient.boardInfoByTheme(themeIdx);
        return  boardInfoDto;
    }
}
