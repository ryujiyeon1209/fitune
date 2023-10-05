package com.fun.fitune.api.service;

import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.repository.ExerciseListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExerciseListService {
    private final ExerciseListRepository exerciseListRepository;

    public List<ExerciseList> showAll(){
        return exerciseListRepository.findAll();
    }
}
