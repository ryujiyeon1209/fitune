package com.fun.fitune.api.controller;

import com.fun.fitune.api.dto.request.PreferExerciseRequest;
import com.fun.fitune.api.dto.response.CommonResponse;
import com.fun.fitune.api.dto.response.PreferExerciseResponse;
import com.fun.fitune.api.service.PreferExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/prefer")
public class PreferExerciseController {

    private final PreferExerciseService preferExerciseService;


    @PostMapping("/add")
    public ResponseEntity<Void> addPreferExercise(@RequestBody PreferExerciseRequest preferExerciseRequest) {
        preferExerciseService.insertPreferExercise(preferExerciseRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{userSeq}")
    public ResponseEntity<PreferExerciseResponse> showPreferExercise(@PathVariable("userSeq") Integer userSeq) {
        return new ResponseEntity<>(preferExerciseService.select(userSeq), HttpStatus.OK);
    }


    @DeleteMapping("/remove/{userSeq}")
    public ResponseEntity<Void> removePreferExercise(@PathVariable("userSeq") Integer userSeq) {
        preferExerciseService.deletePreferExercise(userSeq);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    private <T> CommonResponse<T> makeCommonResponse(String message, T data) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

}
