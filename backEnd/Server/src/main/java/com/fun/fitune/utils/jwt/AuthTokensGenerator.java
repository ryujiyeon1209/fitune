//package com.fun.fitune.utils.jwt;
//
//import com.fun.fitune.exception.CustomException;
//import com.fun.fitune.exception.CustomExceptionList;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.Date;
//
//@Component
//@RequiredArgsConstructor
//public class AuthTokensGenerator {
//    private static final String BEARER_TYPE = "Bearer";
//    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
//    //    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000;            // 1초
//    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 60;  // 두 달
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public AuthTokens generate(Long userId) {
//        long now = (new Date()).getTime();
//        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
//        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
//
//        String subject = userId.toString();
//        String accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt);
//        String refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt);
//
//        return AuthTokens.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
//    }
//
//    public boolean verifyToken(String accessToken) {
//        return jwtTokenProvider.verifyToken(accessToken);
//    }
//
//    public Long extractUserId(String accessToken) {
//        return Long.valueOf(jwtTokenProvider.extractSubject(accessToken));
//    }
//}
