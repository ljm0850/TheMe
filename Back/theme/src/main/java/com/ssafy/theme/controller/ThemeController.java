package com.ssafy.theme.controller;

import com.ssafy.theme.client.UserClient;
import com.ssafy.theme.dto.theme.PublicThemeDto;
import com.ssafy.theme.dto.theme.ThemeDto;
import com.ssafy.theme.dto.theme.ThemeRegistDto;
import com.ssafy.theme.dto.theme.UserThemeDto;
import com.ssafy.theme.dto.theme.UserThemeIdxDto;
import com.ssafy.theme.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
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
    public ResponseEntity<?> getUserThemeList(HttpServletResponse response, @PathVariable(name = "user_id") int user_id) {
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
    @GetMapping("/map/theme") // 공용 테마 목록 조회
    public ResponseEntity<?> getPublicThemeList(HttpServletResponse response, @RequestParam(name = "isMarked") int isMarked,
                        @RequestParam(name ="sort")int sort,@RequestParam(name="pageSize") int pageSize, @RequestParam(name ="pageIdx")int pageIdx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        //임시 JWT 토큰
        int userIdx = 2;
        List<PublicThemeDto>  themeList;
        try {
            if(isMarked == 0){
                themeList = themeService.getPublicThemeList(sort,pageSize,pageIdx);
                result.put("themeList",themeList);
                result.put("message",OK);
            }else if(isMarked == 1){
                themeList = themeService.getBookmarkThemeList(userIdx);
                result.put("themeList",themeList);
                result.put("message",OK);
            }else{
                result.put("message",FAIL);
            }

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

    @GetMapping("/search/{target}")
    public ResponseEntity<?> searchTheme(HttpServletResponse response, @PathVariable(name = "target") String target) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            List<ThemeDto> themeDtos = themeService.searchTheme(target);
            result.put("themeDtos" , themeDtos);
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/bookmark/{user_id}/{theme_idx}")
    public ResponseEntity<?> scrapTheme(HttpServletResponse response, @PathVariable("user_id") int user_id,
                                        @PathVariable(name = "theme_idx") int theme_idx) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            themeService.scrapTheme(user_id, theme_idx);
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/follow")
    public List<UserThemeDto> followThemeList(HttpServletResponse response, @RequestBody UserThemeIdxDto userThemeIdxDto) {
        return themeService.followThemeList(userThemeIdxDto);
    }

    @GetMapping("/live/search")
    public ResponseEntity<?> liveSearchTheme(HttpServletResponse response, @RequestParam(name = "value") String value) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        System.out.println(value);

        try {
            List<String> themeList = themeService.liveSearchTheme(value);
            System.out.println(themeList.size());
            result.put("themeList", themeList);
            result.put("message", OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/name/{theme_idx}")
    public String getThemeName(@PathVariable(name = "theme_idx") int theme_idx) {
        return themeService.getThemeName(theme_idx);
    }
    @GetMapping("/userList/{theme_idx}")
    public List<UserThemeDto> getThemeUserList(@PathVariable(name = "theme_idx") int theme_idx) {
        return themeService.getThemeUserList(theme_idx);
    }
    @GetMapping("/search/theme/info")
    public ResponseEntity<?> serachThemeInfo(@RequestParam(name = "value") String value) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            Map<String, Object> searchResult = themeService.searchThemeInfo(value);
            result.put("isSame", searchResult.get("isSame"));
            result.put("themeList", searchResult.get("result"));
            result.put("message",OK);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }
}
