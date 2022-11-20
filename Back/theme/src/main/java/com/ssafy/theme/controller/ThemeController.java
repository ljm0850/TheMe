package com.ssafy.theme.controller;

import com.ssafy.theme.dto.theme.ThemeRegistDto;
import com.ssafy.theme.service.ThemeService;
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
public class ThemeController {
    private final static String OK = "success";
    private final static String FAIL = "fail";
    ThemeService themeService;
    @Autowired
    ThemeController(ThemeService themeService){
        this.themeService = themeService;
    }
    @PostMapping("/theme")
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
}
