package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.request.BattleRecordRequest;
import com.fun.fitune.api.dto.response.BattleRecordResponse;
import com.fun.fitune.api.service.BattleRecordService;
import com.fun.fitune.db.domain.BattleRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/battle")
public class BattleRecordController {
    private final BattleRecordService battleRecordService;

    @PostMapping("/add")
    public ResponseEntity<String> addBattleRecord(@RequestBody BattleRecordRequest battleRecordRequest) {
        return new ResponseEntity<>(battleRecordService.insertBattleRecord(battleRecordRequest), HttpStatus.OK);
    }

    @GetMapping("/{userSeq}")
    public ResponseEntity<List<BattleRecordResponse>> showBattleRecord(@PathVariable("userSeq") int userSeq) {
        return new ResponseEntity<>(battleRecordService.selectAll(userSeq), HttpStatus.OK);
    }
}
