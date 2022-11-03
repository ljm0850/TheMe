package com.ssafy.user.controller;

import com.ssafy.user.dto.UserLoginDto;
import com.ssafy.user.service.FollowService;
import com.ssafy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
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
    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(HttpServletResponse response, @RequestBody UserLoginDto userLoginDto){
        Map<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
//            userService.loginUser(userLoginDto); // 예시로 만들어 놓은거라 void로 할게요
            response.addHeader("userIdx", "1");
            result.put("message",OK);
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }
        return new ResponseEntity<>(result,status);
    }

    @GetMapping("/user/check")
    public ResponseEntity<?> check(HttpServletResponse response){
        Map<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            result.put("message",OK);
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }
        return new ResponseEntity<>(result,status);
    }

    @PostMapping("/user/follow/{theme_id}/{user_id}/{target_user_id}")
    public ResponseEntity<?> followTheme(HttpServletResponse response, @PathVariable(name = "theme_id") int theme_id, @PathVariable(name = "user_id") int user_id,
                                            @PathVariable(name = "target_user_id") int target_user_id) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            followService.followTheme(theme_id, user_id, target_user_id);
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }

        return new ResponseEntity<>(result,status);
    }

    @PostMapping("/user/follow/{follow_id}")
    public ResponseEntity<?> cancelFollow(HttpServletResponse response, @PathVariable int follow_id) {
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


}
