package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.PreferExerciseRequest;
import com.fun.fitune.api.dto.response.PreferExerciseResponse;
import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.domain.PreferExercise;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.ExerciseListRepository;
import com.fun.fitune.db.repository.PreferExerciseRepository;
import com.fun.fitune.db.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PreferExerciseService {
    private final PreferExerciseRepository preferExerciseRepository;
    private final ExerciseListRepository exerciseListRepository;
    private final UserRepository userRepository;

    // 사용자의 선호 운동을 보여준다.
    public List<PreferExercise> selectAll(Integer userSeq) {
        User user = userRepository.findById(userSeq).orElseThrow();
        List<PreferExercise> preferExercises = preferExerciseRepository.findAllByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("user has not found"));
        return preferExercises;
    }

    // 사용자가 선호 운동을 추가한다.
    public void insertPreferExercise(PreferExerciseRequest preferExerciseRequest){
        for (Byte exerciseListSeq : preferExerciseRequest.getExerciseListSeq()) {
            ExerciseList exerciseList = exerciseListRepository.getReferenceById(exerciseListSeq);

            User user = userRepository
                    .findById(preferExerciseRequest.getUserSeq())
                    .orElseThrow(() -> new IllegalArgumentException("user has not found"));

            PreferExercise preferExercise = PreferExercise.builder()
                    .exerciseList(exerciseList)
                    .user(user)
                    .build();

            preferExerciseRepository.save(preferExercise);
        }
    }

    // 사용자가 선호 운동을 삭제한다.
    public void deletePreferExercise(PreferExerciseRequest preferExerciseRequest){
        for (Byte exerciseListSeq : preferExerciseRequest.getExerciseListSeq()) {
            preferExerciseRepository.deleteByExerciseListAndUser(exerciseListSeq, preferExerciseRequest.getUserSeq());
        }
    }
}
