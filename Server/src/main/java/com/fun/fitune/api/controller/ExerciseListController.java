package com.fun.fitune.api.controller;

import com.fun.fitune.api.service.ExerciseListService;
import com.fun.fitune.db.domain.ExerciseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/exerciseList")
public class ExerciseListController {
    private final ExerciseListService exerciseListService;

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseList>> list(){
        return new ResponseEntity<>(exerciseListService.showAll(), HttpStatus.OK);
    }
}
