package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.api.service.UserService;
import com.fun.fitune.db.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name="사용자 관리", description="사용자 정보 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    final String SUCCESS = "SUCCESS";

    //사용자 정보 조회
    @GetMapping("/info/{userSeq}")
    public ResponseEntity<CommonResponse<UserInfoResponse>> getUserInfo(@PathVariable("userSeq") int userSeq){
        System.out.println("유저 정보 조회 ");
        User userInfo = userService.getUserInfo(userSeq);

    }
}
