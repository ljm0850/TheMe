package com.ssafy.user.controller;

import com.ssafy.user.dto.UserLoginDto;
import com.ssafy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(HttpServletResponse response, @RequestBody UserLoginDto userLoginDto){
        Map<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            userService.loginUser(userLoginDto); // 예시로 만들어 놓은거라 void로 할게요
            result.put("message",OK);
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }
        return new ResponseEntity<>(result,status);
    }
}
