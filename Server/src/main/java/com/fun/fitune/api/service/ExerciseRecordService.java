package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.ExerciseRecordRequest;
import com.fun.fitune.api.dto.response.CellResponse;
import com.fun.fitune.api.dto.response.ExerciseRecordResponse;
import com.fun.fitune.db.domain.Cell;
import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseRecordService {
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ExerciseListRepository exerciseListRepository;
    private final UserRepository userRepository;
    private final CellRepositoryCustomImpl cellRepositoryCustom;
    private final CellRepository cellRepository;


//    @Transactional
//    public CellResponse insertRecord(Integer userSeq, ExerciseRecordRequest exerciseRecordRequest) {
//        User user = userRepository.findByUserSeq(userSeq).orElseThrow();
//        boolean isRecommended = exerciseRecordRequest.isRecommended();
//        ExerciseList exerciseList = exerciseListRepository.findByExerciseListSeq(exerciseRecordRequest.getExerciseListSeq());
//
//        ExerciseRecord exerciseRecord = ExerciseRecord.builder()
//                .user(user)
//                .exerciseReco(isRecommended)
//                .exerciseList(exerciseList)
//                .exerciseStart(exerciseRecordRequest.getExerciseStart())
//                .exerciseEnd(exerciseRecordRequest.getExerciseEnd())
//                .exerciseAvgBpm(exerciseRecordRequest.getExerciseAvgBpm())
//                .exerciseMaxBpm(exerciseRecordRequest.getExerciseMaxBpm())
//                .exerciseReview(exerciseRecordRequest.getReview())
//                .exerciseWeather(exerciseRecordRequest.getWeather())
//                .build();
//
//        exerciseRecordRepository.save(exerciseRecord);
//
//        cellRepositoryCustom.increaseCellExp(user, 123);
//
//        CellResponse cellResponse = CellRepository(cellRepository.findByUser(user).orElseThrow());
//
//        return ;
//    }


    public List<ExerciseRecordResponse> selectAll(int userSeq) {
        List<ExerciseRecordResponse> exerciseRecordResponseList = new ArrayList<>();

        User user = userRepository.findByUserSeq(userSeq).orElseThrow();

        List<ExerciseRecord> exerciseRecordList = exerciseRecordRepository.findAllByUserOrderByExerciseEndDesc(user).orElseThrow();

        for (ExerciseRecord exerciseRecord : exerciseRecordList) {
            ExerciseList exerciseList = exerciseListRepository.findByExerciseListSeq(exerciseRecord.getExerciseList().getExerciseListSeq());

            ExerciseRecordResponse exerciseRecordResponse = ExerciseRecordResponse.builder()
                    .exerciseName(exerciseList.getExerciseName())
                    .exerciseStart(exerciseRecord.getExerciseStart())
                    .exerciseEnd(exerciseRecord.getExerciseEnd())
                    .avgBpm(exerciseRecord.getExerciseAvgBpm())
                    .maxBpm(exerciseRecord.getExerciseMaxBpm())
                    .review(exerciseRecord.getExerciseReview())
                    .build();

            exerciseRecordResponseList.add(exerciseRecordResponse);
        }

        return exerciseRecordResponseList;
    }
}
