package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.request.ExerciseRecordRequest;
import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.service.ExerciseRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/record")
public class ExerciseRecordController {

    private final ExerciseRecordService exerciseRecordService;
    final String SUCCESS = "SUCCESS";


    @GetMapping("/list/{userSeq}")
    public ResponseEntity<CommonResponse> showAll(@PathVariable("userSeq") int userSeq) {
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, exerciseRecordService.selectAll(userSeq)), HttpStatus.OK);
    }


    @PostMapping("/{userSeq}")
    public ResponseEntity<CommonResponse> createRecord(@PathVariable("userSeq") int userSeq, @RequestBody ExerciseRecordRequest exerciseRecordRequest) {
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, exerciseRecordService.insertRecord(userSeq, exerciseRecordRequest)), HttpStatus.OK);
    }


    private <T> CommonResponse<T> makeCommonResponse(String message, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

}
