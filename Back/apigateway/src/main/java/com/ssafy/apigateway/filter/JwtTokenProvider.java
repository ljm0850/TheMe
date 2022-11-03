package com.ssafy.apigateway.filter;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    private String secretKey = "secretKey";
    private final long accessTokenValidMillisecond = 1000L * 60 * 60; // 1시간 유효 토큰
    private final long refreshTokenValidMillisecond = 1000L * 60 * 60 * 24; // 하루 유효 토큰

    // JwtTokenProvider 시작될 때 초기화
    @PostConstruct
    protected void init(){
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    //JWT access-token 생성
    public String createAccessToken(String userIdx){
        log.info("[createToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(userIdx);

        claims.put("role", "USER");
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("[createToken] 토큰 생성 완료");
        return token;
    }

    //JWT refresh-token 생성
    public String createRefreshToken(String userIdx){

        log.info("[createRefreshToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(userIdx);
        claims.put("role", "USER");
        Date now = new Date();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("[createRefreshToken] 토큰 생성 완료, {}", refreshToken);

        return refreshToken;
    }

    public String getUsername(String token) {
        log.info("토큰 기반 회원 구별 정보 추출");
//        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

        Map<String, Object> map = new HashMap<>();
        String[] params = token.split("\\.");

        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(params[1]));
        payload = payload.replaceAll("\\{", "");

        String[] values = payload.split("\\,");

        String name = values[0].split("\\:")[0];
        name = name.replaceAll("\"", "");
        String value = values[0].split("\\:")[1];
        value = value.replaceAll("\"", "");
        map.put(name, value);

        return (String) map.get("sub");
    }

    // 쿠키에 있는 값 가져오기
    public String getCookie(ServerHttpRequest request) {
        log.info("쿠키에 있는 값 가져오겠다..");
        String refreshToken = null;
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();

        log.info("쿠키가 있다면 refreshToken 들고오기");
        Cookie cookie = (Cookie) cookies.get("refreshToken");
        if(cookie == null) System.out.println("쿠키 없다");

        if(cookie != null) refreshToken = cookie.getName();

        return refreshToken;
    }
    // 쿠키 삭제하기
    public void delCookie(ServerHttpRequest request) {
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        Cookie cookie = (Cookie) cookies.get("refreshToken");
        cookie.setMaxAge(Duration.ofDays(0));
    }

    public String validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return "ACCESS";
        } catch (ExpiredJwtException e){
            // 만료된 경우에는 refresh token을 확인하기 위해
            return "EXPIRED";
        } catch (JwtException | IllegalArgumentException e) {
            log.info("jwtException : {}", e);
        }
        return "DENIED";
    }
}
