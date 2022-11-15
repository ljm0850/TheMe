package com.ssafy.user.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.user.client.FeedClient;
import com.ssafy.user.client.ThemeClient;
import com.ssafy.user.dto.*;
import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import com.ssafy.user.service.FollowService;
import com.ssafy.user.service.UserService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    FollowRepository followRepository;
    FollowService followService;
    ThemeClient themeClient;
    FeedClient feedClient;

    @Autowired
    UserServiceImpl(UserRepository userRepository, FollowRepository followRepository,
                    FollowService followService, ThemeClient themeClient, FeedClient feedClient) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.followService = followService;
        this.themeClient = themeClient;
        this.feedClient = feedClient;
    }

    @Override
    public UserInfoByIdDto loginUser(String kakaoToken) { // 로그인

        KakaoDto userRegister = kakaoUser(kakaoToken);

        if(userRegister.getId()==null) throw new IllegalArgumentException("카카오 사용자 가져오기 실패");
        System.out.println("카카오 정보 잘 넘어왔니?");

        UserInfoByIdDto userInfoByIdDto = null;
        if(!userRepository.existsById(userRegister.getId())){ // 없는 회원이면 회원가입
            User user = User.builder()
                    .alertCount(0)
                    .createTime(LocalDateTime.now())
                    .id(userRegister.getId())
                    .picture(userRegister.getPicture())
                    .build();

            // 랜덤 닉네임 생성
            String randomNick = null;
            try {
                randomNick = makeRandomNickname();
            } catch (Exception e) {
                e.printStackTrace();
            }

            while(userRepository.existsByNickname(randomNick)){
                log.info("중복 닉네임");
                try {
                    randomNick = makeRandomNickname();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            user.updateNickname(randomNick);
            userRepository.save(user);

            userInfoByIdDto = UserInfoByIdDto.builder()
                    .kakaoId(user.getId())
                    .nickname(user.getNickname())
                    .userIdx(user.getIdx())
                    .createTime(user.getCreateTime())
                    .description(user.getDescription())
                    .picture(user.getPicture())
                    .userIdx(user.getIdx())
                    .build();

            log.info(userInfoByIdDto.toString());
        } else {

            User user = userRepository.findById(userRegister.getId());
            userInfoByIdDto = UserInfoByIdDto.builder()
                    .kakaoId(user.getId())
                    .nickname(user.getNickname())
                    .userIdx(user.getIdx())
                    .createTime(user.getCreateTime())
                    .description(user.getDescription())
                    .picture(user.getPicture())
                    .userIdx(user.getIdx())
                    .build();
        }
        return userInfoByIdDto;
    }

    @Override
    public UserInfoDto getUserInfo(String nickname,int userIdx) { // 마이페이지 조회 -> userIdx는 현재 보고있는 사람
        User user = userRepository.findByNickname(nickname).orElseThrow(IllegalAccessError::new);
        Optional<User> followUser = userRepository.findById(userIdx);
        int pageUserIdx = user.getIdx();
        boolean isMy = false;
        if(pageUserIdx==userIdx) isMy = true;
        // 해당 유저의 프로필 정보
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .nickname(user.getNickname())
                .description(user.getDescription())
                .picture(user.getPicture())
                .follower(followService.getFollowingList(pageUserIdx).size())
                .following(followService.getFollowerList(pageUserIdx).size())
                .userIdx(user.getIdx())
                .build();
        // 내가 쓴 테마 리스트
        List<UserThemeDto> userThemeDtoList = themeClient.getUserThemeByUserIdx(pageUserIdx);
        userInfoDto.setThemes(userThemeDtoList.size());
        List<UserThemeListDto> userThemeListDtoList = new ArrayList<>(); // 테마 리스트 관련 정보 넣기
        for(int i=0;i<userThemeDtoList.size();i++){
            if(!followUser.isPresent()) continue;
            Optional<Follow> userFollow = followRepository.findByFollowingUserAndFollowUserAndThemeIdx(user,followUser.get(),userThemeDtoList.get(i).getThemeIdx());
            boolean isfollow = false;
            BoardInfoForUserDto boardInfoForUserDto = boardInfoForUser(userThemeDtoList.get(i).getThemeIdx(),userIdx);
            if(userFollow.isPresent()) isfollow = true;
            UserThemeListDto userThemeListDto = UserThemeListDto.builder()
                    .allChallengeCount(boardInfoForUserDto.getAllBoardCount())
                    .boardCount(boardInfoForUserDto.getBoardCount())
                    .challenge(userThemeDtoList.get(i).isChallenge())
                    .commentCount(boardInfoForUserDto.getCommentCount())
                    .currentChallengeCount(boardInfoForUserDto.getCurrentBoardCount())
                    .emoticon(userThemeDtoList.get(i).getEmoticon())
                    .follow(isfollow)
                    .createTime(userThemeDtoList.get(i).getCreateTime())
                    .isMy(isMy)
                    .description(userThemeDtoList.get(i).getDescription())
                    .modifyTime(userThemeDtoList.get(i).getModifyTime())
                    .name(userThemeDtoList.get(i).getName())
                    .openType(userThemeDtoList.get(i).getOpenType())
                    .personCount(boardInfoForUserDto.getPersonCount())
                    .themeIdx(userThemeDtoList.get(i).getThemeIdx())
                    .pictures(boardInfoForUserDto.getPictures())
                    .userIdx(userThemeDtoList.get(i).getUserIdx())
                    .userThemeIdx(userThemeDtoList.get(i).getIdx())
                    .build();
            userThemeListDtoList.add(userThemeListDto);
        }
        userInfoDto.setThemeDtoList(userThemeListDtoList);

        // 내가 쓴 게시글 수
        List<BoardDto> userBoardList = feedClient.userBoardList(user.getIdx());
        userInfoDto.setPosts(userBoardList.size());

        // 내가 팔로우한 테마 리스트
        UserThemeIdxDto userThemeIdxDto = UserThemeIdxDto.builder()
                .userThemeList(followRepository.findThemeIdByFollowingUser(user))
                .build();

        List<UserThemeDto> userThemeListDtos = themeClient.followThemeList(userThemeIdxDto);
        List<UserThemeListDto> userThemeListDtoLists = new ArrayList<>(); // 테마 리스트 관련 정보 넣기

        for(int i=0;i<userThemeListDtos.size();i++){
            boolean isfollow = true;
            BoardInfoForUserDto boardInfoForUserDto = boardInfoForUser(userThemeListDtos.get(i).getThemeIdx(),pageUserIdx);
            Optional<User> followingUser = userRepository.findById(userThemeListDtos.get(i).getUserIdx());
            if(!followingUser.isPresent()) continue;
            Optional<Follow> byFollowUserAndFollowingUserAndThemeIdx = followRepository.findByFollowUserAndFollowingUserAndThemeIdx(user, followingUser.get(), userThemeListDtos.get(i).getIdx());
            if(!byFollowUserAndFollowingUserAndThemeIdx.isPresent()) continue;
            UserThemeListDto userThemeListDto = UserThemeListDto.builder()
                    .allChallengeCount(boardInfoForUserDto.getAllBoardCount())
                    .boardCount(boardInfoForUserDto.getBoardCount())
                    .challenge(userThemeListDtos.get(i).isChallenge())
                    .commentCount(boardInfoForUserDto.getCommentCount())
                    .currentChallengeCount(boardInfoForUserDto.getCurrentBoardCount())
                    .emoticon(userThemeListDtos.get(i).getEmoticon())
                    .follow(isfollow)
                    .createTime(userThemeListDtos.get(i).getCreateTime())
                    .isMy(false)
                    .description(userThemeListDtos.get(i).getDescription())
                    .modifyTime(userThemeListDtos.get(i).getModifyTime())
                    .name(userThemeListDtos.get(i).getName())
                    .openType(userThemeListDtos.get(i).getOpenType())
                    .personCount(boardInfoForUserDto.getPersonCount())
                    .themeIdx(userThemeListDtos.get(i).getThemeIdx())
                    .pictures(boardInfoForUserDto.getPictures())
                    .userIdx(userThemeListDtos.get(i).getUserIdx())
                    .userThemeIdx(userThemeListDtos.get(i).getIdx())
                    .followIdx(byFollowUserAndFollowingUserAndThemeIdx.get().getIdx())
                    .build();
            userThemeListDtoLists.add(userThemeListDto);
        }
        userInfoDto.setFollowingDtoList(userThemeListDtoLists);
        return userInfoDto;
    }

    @Override
    public boolean isPossibleNickname(String nickname) { // 닉네임 중복검사
        // true이면 사용가능, false일 때 이미 있는 닉네임
        return !userRepository.existsByNickname(nickname);
    }

    @Override
    public void updateUser(String nickname, UserUpdateDto userUpdate) { // 회원정보 수정

        User user = userRepository.findByNickname(nickname).orElseThrow(IllegalAccessError::new);

        user.updateNickname(userUpdate.getNickname());
        user.updateDescription(userUpdate.getDescription());
        user.updatePicture(userUpdate.getPicture());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String nickname) { // 회원탈퇴
        User user = userRepository.findByNickname(nickname).orElseThrow(IllegalAccessError::new);

        List<Follow> followList = followRepository.findByFollowUserOrFollowingUser(user, user);

        for(int i=0; i<followList.size(); i++){
            followRepository.delete(followList.get(i));
        }

        userRepository.delete(user);
    }

    @Override
    public UserInfoByIdDto getUserInfoById(int userIdx) {
        User user = userRepository.findById(userIdx)
                .orElseThrow(IllegalAccessError::new);

        UserInfoByIdDto userInfoByIdDto = UserInfoByIdDto.builder()
                .createTime(user.getCreateTime())
                .userIdx(user.getIdx())
                .kakaoId(user.getId())
                .email(user.getEmail())
                .description(user.getDescription())
                .picture(user.getPicture())
                .nickname(user.getNickname())
                .build();

        return userInfoByIdDto;
    }

    public KakaoDto kakaoUser(String token){
        KakaoDto kakaoDto = new KakaoDto();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        // access_token을 이용하여 사용자 정보 조회
        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            int responseCode = conn.getResponseCode();
            log.info("responsecode, {}", responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while((line = br.readLine()) != null){
                result += line;
            }
            log.info("response body : {}", result);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);

            String id = jsonNode.get("id").asText();
//            String email = jsonNode.get("kakao_account").get("email").asText();
            String nickname = jsonNode.get("properties")
                    .get("nickname").asText();
            String picture = jsonNode.get("properties")
                    .get("profile_image").asText();

            System.out.println(id+" "+nickname+" "+picture);

            kakaoDto = KakaoDto.builder()
                    .id(id)
                    .nickname(nickname)
                    .picture(picture)
                    .build();
            log.info(kakaoDto.toString());

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return kakaoDto;
    }

    public String makeRandomNickname() throws Exception {
        // REST API 호출
        URL url = new URL("https://nickname.hwanmoo.kr/?format=json&count=1&max_length=4");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json"); // Content-Type 지정
        conn.setDoOutput(true); // 출력 가능 상태로 변경
        conn.connect();

        // 데이터  읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while((line = br.readLine()) != null) {
            sb.append(line); // StringBuilder 사용시 객체를 계속 생성하지 않고 하나의 객체릂 수정하므로 더 빠름.
        }
        conn.disconnect();

        // JSON Parsing
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(sb.toString());

        // 이런 방식으로 데이터 꺼낼 수 있음.
        System.out.println(jsonObj.get("words"));
        String nickname = jsonObj.get("words").toString();

        nickname=nickname.replaceAll("\\[", "");
        nickname=nickname.replaceAll("\\]", "");
        nickname=nickname.replaceAll("\"", "");

        return nickname;
    }

    @Override
    public List<UserDto> searchRecommend() {
        List<User> followRank = followRepository.searchRecommned();

        List<UserDto> rankList = new ArrayList<>();
        for(int i=0;i<followRank.size();i++) {
            User user = followRank.get(i);

            UserDto userDto = UserDto.builder()
                    .nickname(user.getNickname())
                    .description(user.getDescription())
                    .picture(user.getPicture())
                    .createTime(user.getCreateTime())
                    .idx(user.getIdx())
                    .email(user.getEmail())
                    .id(user.getId())
                    .alertCount(user.getAlertCount())
                    .build();

            rankList.add(userDto);
        }

        return rankList;
    }

    @Override
    public List<String> liveSearchUser(String value) {
        List<String> strings = userRepository.liveSearchByName(value);

        return strings;
    }

    @Override
    public Map<String, Object> searchThemeInfo(String value) {
        Map<String, Object> answer = new HashMap<>();
        List<UserListDto> result = new ArrayList<>();
        List<String> pictureArr = new ArrayList<>();
        boolean same = userRepository.findByNickname(value).isPresent();
        int sameUserIdx = 0; // 같은 사람 있는 경우 그 사람 번호 여기에 넣어서  두번 출력안되도록 하기
        System.out.println("들어오나");
        if(same) { // 아예 같은 사람
            User user = userRepository.findByNickname(value).orElseThrow(IllegalAccessError::new);
            sameUserIdx = user.getIdx();
            int boardCount = 0;
            int commentCount = 0;
            List<Integer> followList = followRepository.findFollowerByUser(user);
            List<UserThemeDto> userThemeDtoList = themeClient.getUserThemeByUserIdx(user.getIdx());
            for(int i=0;i<userThemeDtoList.size();i++){
                BoardInfoForUserDto boardInfoForUserDto;
                try {
                    boardInfoForUserDto = feedClient.boardInfoForUser(userThemeDtoList.get(i).getIdx(), sameUserIdx);
                    boardCount += boardInfoForUserDto.getBoardCount();
                    commentCount += boardInfoForUserDto.getCommentCount();
                }
                catch (Exception e){
                    continue;
                }
                if(userThemeDtoList.get(i).getOpenType()==0) { // 전체공개인 경우만 사진 받아오기
                    if(boardInfoForUserDto.getPictures().length>0){
                        String[] arr = boardInfoForUserDto.getPictures();
                        pictureArr.add(arr[0]);
                    }
                }
            }
            String[] pictureArray = new String[pictureArr.size()];
            for(int j=0;j< pictureArr.size();j++){
                pictureArray[j] = pictureArr.get(j);
            }
            UserListDto userDto = UserListDto.builder()
                    .alertCount(user.getAlertCount())
                    .idx(user.getIdx())
                    .email(user.getEmail())
                    .id(user.getId())
                    .description(user.getDescription())
                    .picture(user.getPicture())
                    .nickname(user.getNickname())
                    .createTime(user.getCreateTime())
                    .followCount(followList.size())
                    .boardCount(boardCount)
                    .commentCount(commentCount)
                    .pictures(pictureArray)
                    .build();
            result.add(userDto);
        }

        List<User> users = userRepository.searchByTarget(value);
        for(int i=0;i<users.size();i++) {
            pictureArr = new ArrayList<>();
            User user = users.get(i);
            if(user.getIdx()!=sameUserIdx){
                int boardCount = 0;
                int commentCount = 0;
                List<Integer> followList = followRepository.findFollowerByUser(user);
                List<UserThemeDto> userThemeDtoList = themeClient.getUserThemeByUserIdx(user.getIdx());
                for(int j=0;j<userThemeDtoList.size();j++){
                    System.out.println(userThemeDtoList.size());
                    BoardInfoForUserDto boardInfoForUserDto;
                    try {
                        boardInfoForUserDto = feedClient.boardInfoForUser(userThemeDtoList.get(j).getIdx(), user.getIdx());
                        System.out.println(boardInfoForUserDto.toString());
                        boardCount += boardInfoForUserDto.getBoardCount();
                        commentCount += boardInfoForUserDto.getCommentCount();
                    }
                    catch (Exception e){
                        continue;
                    }
                    if(userThemeDtoList.get(j).getOpenType()==0) { // 전체공개인 경우만 사진 받아오기
                        if(boardInfoForUserDto.getPictures().length>0){
                            String[] arr = boardInfoForUserDto.getPictures();
                            pictureArr.add(arr[0]);
                        }
                    }
                }
                String[] pictureArray = new String[pictureArr.size()];
                for(int j=0;j< pictureArr.size();j++){
                    pictureArray[j] = pictureArr.get(j);
                }
                UserListDto userDto = UserListDto.builder()
                        .alertCount(user.getAlertCount())
                        .idx(user.getIdx())
                        .email(user.getEmail())
                        .id(user.getId())
                        .description(user.getDescription())
                        .picture(user.getPicture())
                        .nickname(user.getNickname())
                        .createTime(user.getCreateTime())
                        .followCount(followList.size())
                        .boardCount(boardCount)
                        .commentCount(commentCount)
                        .pictures(pictureArray)
                        .build();
                result.add(userDto);
            }
        }
        answer.put("result",result);
        answer.put("isSame", same);
        return answer;
    }
    @Override
    public void alertUser(int userIdx) {
        User user = userRepository.findById(userIdx)
                .orElseThrow(IllegalAccessError::new);

        user.updateAlertCount();

        userRepository.save(user);
    }
    @Override
    public BoardInfoForUserDto boardInfoForUser(int themeIdx, int userIdx){
        return feedClient.boardInfoForUser(themeIdx,userIdx);
    }
}
