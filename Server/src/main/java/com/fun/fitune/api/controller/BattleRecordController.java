package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.request.BattleRecordRequest;
import com.fun.fitune.api.dto.response.BattleRecordResponse;
import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.service.BattleRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/battle")
public class BattleRecordController {

    private final BattleRecordService battleRecordService;
    final String SUCCESS = "SUCCESS";


    @PostMapping("/add")
    public ResponseEntity<CommonResponse> addBattleRecord(@RequestBody BattleRecordRequest battleRecordRequest) {
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, battleRecordService.insertBattleRecord(battleRecordRequest)), HttpStatus.OK);
    }


    @GetMapping("/{userSeq}")
    public ResponseEntity<CommonResponse> showOpponent(@PathVariable("userSeq") int userSeq){
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, battleRecordService.selectOpponent(userSeq)), HttpStatus.OK);
    }


    @GetMapping("/record/{userSeq}")
    public ResponseEntity<CommonResponse> showBattleRecord(@PathVariable("userSeq") int userSeq) {
        return new ResponseEntity<>(makeCommonResponse(SUCCESS, battleRecordService.selectAll(userSeq)), HttpStatus.OK);
    }



    private <T> CommonResponse<T> makeCommonResponse(String message, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

}
