package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.api.service.UserService;
import com.fun.fitune.db.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @Parameter(name = "userSeq", description = "조회할 사용자의 userId")
    @GetMapping("/info/{userSeq}")
    public ResponseEntity<CommonResponse<UserInfoResponse>> getUserInfo(@PathVariable("userSeq") int userSeq){
        System.out.println("유저 정보 조회 ");
        User userInfo = userService.getUserInfo(userSeq);
        return new ResponseEntity<>(
                makeCommonResponse(SUCCESS, new UserInfoResponse(userInfo)), HttpStatus.OK);
    }
    //사용자 이름 변경

    //사용자 운동세포 이름 변경

    //사용자 정보 변경

    //

    private <T> CommonResponse<T> makeCommonResponse(String message, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

}
