package com.ssafy.user.controller;

import com.ssafy.user.dto.*;
import com.ssafy.user.service.FollowService;
import com.ssafy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/user")
public class UserController {
    private final static String OK = "success";
    private final static String FAIL = "fail";
    UserService userService;
    FollowService followService;
    @Autowired
    UserController(UserService userService, FollowService followService){
        this.userService = userService;
        this.followService = followService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(HttpServletResponse response, @RequestBody UserLoginDto userLoginDto){
        Map<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {

            UserInfoByIdDto userInfoDto = userService.loginUser(userLoginDto.getKakaoToken());
            response.setHeader("userIdx", Integer.toString(userInfoDto.getUserIdx()));
            result.put("message",OK);
            result.put("userInfo", userInfoDto);
            status = HttpStatus.OK;

        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }
        return new ResponseEntity<>(result,status);
    }

    @PostMapping("/follow/{theme_id}/{target_user_id}")
    public ResponseEntity<?> followTheme(HttpServletRequest request, @PathVariable(name = "theme_id") int theme_id, @PathVariable(name = "target_user_id") int target_user_id) {
        Map<String, Object> result = new HashMap<>();
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            followService.followTheme(theme_id, userIdx, target_user_id);
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }

        return new ResponseEntity<>(result,status);
    }

    @DeleteMapping("/follow/{follow_id}")
    public ResponseEntity<?> cancelFollow(@PathVariable int follow_id) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try{
            followService.cancelFollow(follow_id);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/info/{nickname}")
    ResponseEntity<?> getUserInfo(HttpServletRequest request,@PathVariable(name = "nickname") String nickname) {
        Map<String, Object> result = new HashMap<>();
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            UserInfoDto userInfo = userService.getUserInfo(nickname,userIdx);
            result.put("message", OK);
            result.put("userInfo", userInfo);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
            System.out.println(e);
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/duplication/{nickname}")
    ResponseEntity<?> duplicateNickname(@PathVariable(name = "nickname") String nickname) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            boolean isPossible = userService.isPossibleNickname(nickname);
            result.put("message", OK);
            result.put("isPossible", isPossible);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @PutMapping("/info/{nickname}")
    ResponseEntity<?> updateUserInfo(@PathVariable(name = "nickname") String nickname,
                                     @RequestBody UserUpdateDto userUpdateDto) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            userService.updateUser(nickname, userUpdateDto);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @DeleteMapping("/{nickname}")
    ResponseEntity<?> deleteUser(@PathVariable(name = "nickname") String nickname) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            userService.deleteUser(nickname);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/following/theme/{user_id}")
    ResponseEntity<?> getFollowingThemeIdxList(@PathVariable(name = "user_id") int user_id) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            List<Integer> followingThemeIdxList = followService.getFollowingThemeList(user_id);
            result.put("message", OK);
            result.put("followingThemeIdxList", followingThemeIdxList);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/follower/{user_id}")
    ResponseEntity<?> getFollowerList(@PathVariable(name = "user_id") int user_id) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            List<String> followerList = followService.getFollowerList(user_id);
            result.put("message", OK);
            result.put("followerList", followerList);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/following/{user_id}")
    ResponseEntity<?> getFollowingList(@PathVariable(name = "user_id") int user_id) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            List<String> followingList = followService.getFollowingList(user_id);
            result.put("message", OK);
            result.put("followingList", followingList);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @DeleteMapping("/unfollow/{target_user_id}")
    ResponseEntity<?> unfollowUser(HttpServletRequest request,
                                   @PathVariable(name = "target_user_id") int target_user_id) {
        Map<String, Object> result = new HashMap<>();
        int userIdx = Integer.parseInt(request.getHeader("userIdx"));
        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            followService.cancelUserFollow(target_user_id, userIdx);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/info/id/{user_id}")
    UserInfoByIdDto getUserInfoById(@PathVariable(name = "user_id") int user_id) {
        return userService.getUserInfoById(user_id);
    }

    @GetMapping("/search/recommend")
    public List<UserDto> searchRecommend() {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;

        List<UserDto> userDtos = userService.searchRecommend();

       return userDtos;
    }

    @GetMapping("/live/search")
    public ResponseEntity<?> liveSearchUser(HttpServletResponse response, @RequestParam(name = "value") String value) {
        Map<String, Object> result = new HashMap<>();

        HttpStatus status  = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            List<String> userList = userService.liveSearchUser(value);
            result.put("userList", userList);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/search/user/info")
    public ResponseEntity<?> searchUserInfo(@RequestParam(name = "value") String value) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            Map<String, Object> searchResult = userService.searchThemeInfo(value);
            result.put("isSame", searchResult.get("isSame"));
            result.put("userList", searchResult.get("result"));
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }
    @GetMapping("/following/themeList/{user_idx}")
    List<UserFollowThemeDto> getUserFollowTheme(@RequestParam(name = "user_idx") int userIdx) {
        return followService.getUserFollowTheme(userIdx);
    }

    @PostMapping("/alertCount/{user_idx}")
    String alertUser(@PathVariable(name = "user_idx") int user_idx){
        try {
            userService.alertUser(user_idx);
        } catch (Exception e){
            return FAIL;
        }

        return OK;
    }

    @GetMapping("/follow/recommend")
    List<Integer> getRecommendThemeList(){
        return followService.getRecommendThemeList();
    }

}
