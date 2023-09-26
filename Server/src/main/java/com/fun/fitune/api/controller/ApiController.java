package com.fun.fitune.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fun.fitune.api.dto.request.RecommendRequest;
import com.fun.fitune.api.dto.request.UserInfoRequest;
import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.RecommendResponse;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.api.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/data/django")
public class ApiController {

    private final RestTemplateService templateService;
    final String SUCCESS = "SUCCESS";

    @PostMapping("/recommend")
    public ResponseEntity<CommonResponse<RecommendResponse>> makeRecommendList(@RequestBody RecommendRequest recommendRequest) throws JsonProcessingException {
        RecommendResponse response = templateService.recommend(recommendRequest);

        return new ResponseEntity<>(
                makeCommonResponse(SUCCESS, response), HttpStatus.OK);
    }


    private <T> CommonResponse<T> makeCommonResponse(String message, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }
}
