//package com.ssafy.apigateway.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.Objects;
//
//@Component
//public class CustomJwtProviderFilter extends AbstractGatewayFilterFactory<CustomJwtProviderFilter.Config> {
//
//    JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public CustomJwtProviderFilter(JwtTokenProvider jwtTokenProvider){
//        super(Config.class);
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) ->{
//            ServerHttpResponse response = exchange.getResponse();
//            //Custom Post Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(()->{
//                // 로그인 성공 시 token 발급
//                List<String> token = response.getHeaders().get("userIdx");
//                String userIdx = Objects.requireNonNull(token).get(0);
//                if(userIdx != null){
//                    // header에 accessToken 넘기기
//                    String accessToken = jwtTokenProvider.createAccessToken(userIdx);
//                    String refreshToken = jwtTokenProvider.createRefreshToken(userIdx);
//                    exchange.getResponse().getHeaders().set("authorization", accessToken);
//
//                    jwtTokenProvider.delCookie(exchange.getRequest());
//
//                    // 리프레시토큰 쿠키에 저장
//                    ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
//                            .sameSite("None")
//                            .secure(true)
//                            .path("/")
//                            .maxAge(24 * 60 * 60)
//                            .build();
//
//                    response.addCookie(cookie);
//                }
//            }));
//        };
//    }
//
//    public static class Config{
//        //Put the configuration properties
//    }
//}
