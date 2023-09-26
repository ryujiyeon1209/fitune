package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.PreferExerciseRequest;
import com.fun.fitune.api.dto.response.PreferExerciseResponse;
import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.PreferExercise;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.ExerciseListRepository;
import com.fun.fitune.db.repository.PreferExerciseRepository;
import com.fun.fitune.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreferExerciseService {
    private final PreferExerciseRepository preferExerciseRepository;
    private final ExerciseListRepository exerciseListRepository;
    private final UserRepository userRepository;

    // 사용자의 선호 운동을 보여준다.
    public PreferExerciseResponse select(Integer userSeq) {
        User user = userRepository.findById(userSeq).orElseThrow();

        PreferExercise preferExercise = preferExerciseRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("user has not found"));

        PreferExerciseResponse preferExerciseResponse = PreferExerciseResponse.builder()
                .exerciseName(exerciseListRepository.findByExerciseListSeq(preferExercise.getExerciseList().getExerciseListSeq()).getExerciseName())
                .exerciseListSeq(preferExercise.getExerciseList().getExerciseListSeq())
                .build();

        return preferExerciseResponse;
    }

    // 사용자가 선호 운동을 추가한다.
    @Transactional
    public void insertPreferExercise(PreferExerciseRequest preferExerciseRequest) {
        User user = userRepository
                    .findById(preferExerciseRequest.getUserSeq())
                    .orElseThrow(() -> new IllegalArgumentException("user has not found"));

        ExerciseList exerciseList = exerciseListRepository.findByExerciseListSeq(preferExerciseRequest.getExerciseListSeq());

        PreferExercise preferExercise = PreferExercise.builder()
                .exerciseList(exerciseList)
                .user(user)
                .build();

        if (preferExerciseRepository.findByUser(user).orElseThrow() != null){
            preferExerciseRepository.save(preferExercise);
        }
        else {
            deletePreferExercise(preferExerciseRequest.getUserSeq());

            preferExerciseRepository.save(preferExercise);
        }
    }

    // 사용자가 선호 운동을 삭제한다.
    @Transactional
    public void deletePreferExercise(int userSeq) {
        User user = userRepository.findByUserSeq(userSeq).orElseThrow();

        preferExerciseRepository.deleteByExerciseListAndUser(user);
    }
}
