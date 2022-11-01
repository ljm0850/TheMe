package com.ssafy.user.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.user.client.ThemeClient;
import com.ssafy.user.dto.*;
import com.ssafy.user.entity.Follow;
import com.ssafy.user.entity.User;
import com.ssafy.user.repository.FollowRepository;
import com.ssafy.user.repository.UserRepository;
import com.ssafy.user.service.FollowService;
import com.ssafy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
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
    public void loginUser(String kakaoToken) { // 로그인
        String kakaoAccessToken = getKakaoAccessToken(kakaoToken);
        kakaoDto userRegister = null;
        if(!kakaoAccessToken.equals("")) userRegister = kakaoUser(kakaoAccessToken);
        else throw new IllegalArgumentException("accessToken 가져오기 실패");

        if(!userRepository.existsById(userRegister.getId()) && userRegister.getId() !=null){ // 없는 회원이면 회원가입
            User user = User.builder()
                    .alertCount(0)
                    .createTime(LocalDateTime.now())
                    .email(userRegister.getEmail())
                    .id(userRegister.getId())
                    .nickname(userRegister.getNickname())
                    .picture(userRegister.getPicture())
                    .build();

            userRepository.save(user);
        }

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

    public String getKakaoAccessToken (String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=bab0a08f8b68900521759c285635e38a"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://k7c2031.p.ssafy.io:8080/"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public kakaoDto kakaoUser(String token){
        String id = null;
        String email = null;
        String nickname =null;
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

            id = jsonNode.get("id").asText();
            email = jsonNode.get("kakao_account").get("email").asText();
            nickname = jsonNode.get("properties")
                    .get("nickname").asText();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        kakaoDto kakao = kakaoDto.builder()
                .email(email)
                .id(id)
                .nickname(nickname)
                .build();

        return kakao;
    }
}
