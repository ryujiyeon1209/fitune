//package com.fun.fitune.interceptor;
//
//import com.fun.fitune.exception.CustomException;
//import com.fun.fitune.exception.CustomExceptionList;
//import com.fun.fitune.utils.jwt.AuthTokensGenerator;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class AuthInterceptor implements HandlerInterceptor {
//
//    private final AuthTokensGenerator authTokensGenerator;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (CorsUtils.isPreFlightRequest(request)) {
//            return true;
//        }
//
//        if (request.getRequestURI().contains("gwh-websocket")) {
//            return true;
//        }
//
//        String token = request.getHeader("token");
//        if (token != null && authTokensGenerator.verifyToken(token)) {
//            log.info("access confirmed");
//            return true;
//        }
//        log.error("access denied");
//        log.error(request.getRequestURI());
//        throw new CustomException(CustomExceptionList.ACCESS_TOKEN_ERROR);
//    }
//}