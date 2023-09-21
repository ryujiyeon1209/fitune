package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.request.ExerciseRecordRequest;
import com.fun.fitune.api.dto.response.ExerciseRecordResponse;
import com.fun.fitune.api.service.ExerciseRecordService;
import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.domain.ExerciseRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/record")
public class ExerciseRecordController {
    private final ExerciseRecordService exerciseRecordService;

    @PostMapping("/start/{userSeq}")
    public ResponseEntity<Void> createRecord(@PathVariable("userSeq") int userSeq, @RequestBody ExerciseRecordRequest exerciseRecordRequest) {
        exerciseRecordService.insertRecord(userSeq, exerciseRecordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userSeq}")
    public ResponseEntity<List<ExerciseRecordResponse>> showAll(@PathVariable("userSeq") int userSeq){
        return new ResponseEntity<>(exerciseRecordService.selectAll(userSeq), HttpStatus.OK);
    }

    @PatchMapping("/end/{userSeq}")
    public ResponseEntity<Void> modifyRecord(@PathVariable("userSeq") int userSeq, @RequestBody ExerciseRecordRequest exerciseRecordRequest){
        exerciseRecordService.updateRecord(userSeq, exerciseRecordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
