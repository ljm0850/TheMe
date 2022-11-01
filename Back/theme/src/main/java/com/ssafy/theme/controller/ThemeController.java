package com.ssafy.theme.controller;

import com.ssafy.theme.client.UserClient;
import com.ssafy.theme.dto.theme.ThemeRegistDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    private final static String OK = "success";
    private final static String FAIL = "fail";
    ThemeService themeService;
    @Autowired
    ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping("")
    public ResponseEntity<?> registTheme(HttpServletResponse response, @RequestBody ThemeRegistDto themeRegistDto){
        Map<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            themeService.registTheme(themeRegistDto);
            result.put("message",OK);
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);
        }
        return new ResponseEntity<>(result,status);
    }

    @PostMapping("/userThema")
    public ResponseEntity<?> createUserTheme(HttpServletResponse response, @RequestBody UserThemeDto userThemeDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            themeService.createUserTheme(userThemeDto);
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("message",FAIL);

        }
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getThemeList(HttpServletResponse response, @PathVariable(name = "user_id") int user_id) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            List<UserThemeDto> themeList = themeService.getThemeList(user_id);
            result.put("themeList",themeList);
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/test/{nickname}")
    public ResponseEntity<?> test(HttpServletResponse response, @PathVariable(name = "nickname") String nickname) {
        ResponseEntity<?> result = themeService.getUserInfo(nickname);

        return result;
    }
}
