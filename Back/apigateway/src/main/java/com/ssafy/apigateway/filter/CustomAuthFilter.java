package com.ssafy.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
public class CustomAuthFilter extends AbstractGatewayFilterFactory<CustomAuthFilter.Config> {

    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CustomAuthFilter(JwtTokenProvider jwtTokenProvider){
        super(Config.class);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public GatewayFilter apply(CustomAuthFilter.Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest(); // Pre Filter
            ServerHttpResponse response = exchange.getResponse(); // Post Filter

            System.out.println(request.getPath().toString());

            if(request.getPath().toString().equals("/user/login")) {
                System.out.println("로그인 하러 갔니?");
                return chain.filter(exchange).then(Mono.fromRunnable(()->{

                    // 로그인 성공 시 token 발급
                    System.out.println(response.getHeaders().get("userIdx"));
                    List<String> token = response.getHeaders().get("userIdx");
                    String userIdx = Objects.requireNonNull(token).get(0);

                    System.out.println(userIdx);

                    if(userIdx != null){
                        // header에 accessToken 넘기기
                        String accessToken = jwtTokenProvider.createAccessToken(userIdx);
                        String refreshToken = jwtTokenProvider.createRefreshToken(userIdx);

                        exchange.getResponse().getHeaders().set("Authorization", accessToken);
                        exchange.getResponse().getHeaders().setAccessControlAllowCredentials(true);

//                        jwtTokenProvider.delCookie(exchange.getRequest());
                        ResponseCookie addAccessToken = ResponseCookie.from("Authorization", accessToken)
                                .sameSite("None")
                                .domain("p.ssafy.io")
                                .path("/")
                                .maxAge(24 * 60 * 60)
                                .build();
                        response.addCookie(addAccessToken);
                        // 리프레시토큰 쿠키에 저장
                        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                                .sameSite("None")
                                .secure(true)
                                .domain("p.ssafy.io")
                                .path("/")
                                .maxAge(24 * 60 * 60)
                                .build();

                        response.addCookie(cookie);
                    }
                }));
            }

            // Request Header 에 token 이 존재하지 않을 때
            if(!request.getHeaders().containsKey("Authorization")){
                return handleUnAuthorized(exchange); // 401 Error
            }

            // Request Header 에서 token 문자열 받아오기
            List<String> token = request.getHeaders().get("Authorization");
            String tokenString = Objects.requireNonNull(token).get(0);

            // 토큰 검증
            if(jwtTokenProvider.validateToken(tokenString).equals("DENIED")) {
                return handleUnAuthorized(exchange); // 토큰이 일치하지 않을 때  401 Error
            } else if(jwtTokenProvider.validateToken(tokenString).equals("EXPIRED")){
                // 토큰이 유효하지 않은 경우
                // 쿠키의 refreshToken과 accessToken의 userIdx 확인
                // && refreshToken이 유효한지 확인
//                request.getCookies();
                String refreshToken = jwtTokenProvider.getCookie(request);
                String userIdx = jwtTokenProvider.getUsername(tokenString);

                if(userIdx.equals(jwtTokenProvider.getUsername(refreshToken))
                        &&jwtTokenProvider.validateToken(refreshToken).equals("ACCESS")){

                    // accessToken 재발급 해주기
                    String newToken = jwtTokenProvider.createAccessToken(userIdx);
                    exchange.getRequest().getHeaders().set("userIdx", userIdx);
                    exchange.getRequest().mutate()
                            .headers(httpHeaders -> httpHeaders.add("userIdx", userIdx)).build();

                    return chain.filter(exchange).then(Mono.fromRunnable(()->{
                        exchange.getResponse().getHeaders().set("Authorization", newToken);
                    }));

                } else {
                    return handleUnAuthorized(exchange); // refreshToken이 유효하지 않으므로 401 Error
                }

            } else {
                // 유효한 액세스토큰이면
                String userIdx = jwtTokenProvider.getUsername(tokenString);
                exchange.getRequest().mutate()
                        .headers(httpHeaders -> httpHeaders.add("userIdx", userIdx)).build();
                return chain.filter(exchange);
            }
        });
    }

    private Mono<Void> handleUnAuthorized(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public static class Config {

    }
}
