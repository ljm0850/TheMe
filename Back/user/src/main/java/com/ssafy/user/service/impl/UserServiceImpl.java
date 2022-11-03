package com.ssafy.user.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.user.client.ThemeClient;
import com.ssafy.user.dto.*;
import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import com.ssafy.user.service.FollowService;
import com.ssafy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    FollowRepository followRepository;
    FollowService followService;
    ThemeClient themeClient;

    @Autowired
    UserServiceImpl(UserRepository userRepository, FollowRepository followRepository,
                    FollowService followService, ThemeClient themeClient) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.followService = followService;
        this.themeClient = themeClient;
    }

    @Override
    public UserInfoByIdDto loginUser(String kakaoToken) { // 로그인

        KakaoDto userRegister = kakaoUser(kakaoToken);

        if(userRegister==null) throw new IllegalArgumentException("카카오 사용자 가져오기 실패");
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
                    .build();
        }

        return userInfoByIdDto;
    }

    @Override
    public UserInfoDto getUserInfo(String nickname) { // 마이페이지 조회

        User user = userRepository.findByNickname(nickname);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .nickname(user.getNickname())
                .description(user.getDescription())
                .picture(user.getPicture())
                .follower(followService.getFollowerList(user.getIdx()).size())
                .following(followService.getFollowingList(user.getIdx()).size())
                .build();

        // 내가 쓴 테마 리스트
        /* FeignClient 적용 부분

        ResponseEntity<?> themeDto = themeClient.getThemeList(user.getId());
        themeDto.get("themeList");

         */

        // 내가 쓴 게시글 수
//        int posts =
//        userInfoDto.setPosts();


        // 내가 팔로우한 테마 리스트


        return userInfoDto;
    }

    @Override
    public boolean isPossibleNickname(String nickname) { // 닉네임 중복검사
        // true이면 사용가능, false일 때 이미 있는 닉네임
        return !userRepository.existsByNickname(nickname);
    }

    @Override
    public void updateUser(String nickname, UserUpdateDto userUpdate) { // 회원정보 수정

        User user = userRepository.findByNickname(nickname);

        user.updateNickname(userUpdate.getNickname());
        user.updateDescription(userUpdate.getDescription());
        user.updatePicture(userUpdate.getPicture());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String nickname) { // 회원탈퇴
        User user = userRepository.findByNickname(nickname);

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
        KakaoDto kakaoDto = null;
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
}
