package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.PreferExerciseRequest;
import com.fun.fitune.api.dto.response.PreferExerciseResponse;
import com.fun.fitune.db.domain.ExerciseList;
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
    public List<PreferExerciseResponse> selectAll(Integer userSeq) {
        User user = userRepository.findById(userSeq).orElseThrow();

        List<PreferExercise> preferExercises = preferExerciseRepository.findAllByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("user has not found"));

        List<PreferExerciseResponse> responseDtoList = new ArrayList<>();
        for (PreferExercise preferExercise : preferExercises) {
            responseDtoList.add(
                    PreferExerciseResponse.builder()
                            .exerciseListSeq(preferExercise.getExerciseList().getExerciseListSeq())
                            .exerciseName(preferExercise.getExerciseList().getExerciseName())
                            .build()
            );
        }

        return responseDtoList;
    }

    // 사용자가 선호 운동을 추가한다.
    @Transactional
    public void insertPreferExercise(PreferExerciseRequest preferExerciseRequest) {
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
    @Transactional
    public void deletePreferExercise(PreferExerciseRequest preferExerciseRequest) {
        User user = User.builder()
                .userSeq(preferExerciseRequest.getUserSeq())
                .build();
        for (Byte exerciseListSeq : preferExerciseRequest.getExerciseListSeq()) {
            ExerciseList exerciseList = ExerciseList.builder()
                    .exerciseListSeq(exerciseListSeq)
                    .build();
            preferExerciseRepository.deleteByExerciseListAndUser(exerciseList, user);
        }
    }
}
