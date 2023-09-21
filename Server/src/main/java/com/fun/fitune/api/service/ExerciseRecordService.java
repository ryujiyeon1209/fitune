package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.ExerciseRecordRequest;
import com.fun.fitune.api.dto.response.ExerciseRecordResponse;
import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.ExerciseListRepository;
import com.fun.fitune.db.repository.ExerciseRecordRepository;
import com.fun.fitune.db.repository.ExerciseRecordRepositoryCustomImpl;
import com.fun.fitune.db.repository.UserRepository;
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
    private final ExerciseRecordRepositoryCustomImpl exerciseRecordRepositoryCustom;
    private final ExerciseListRepository exerciseListRepository;
    private final UserRepository userRepository;

    @Transactional
    public Integer insertRecord(Integer userSeq, ExerciseRecordRequest exerciseRecordRequest){
        Byte exerciseListSeq = exerciseRecordRequest.getExerciseListSeq();
        boolean isRecommended = exerciseRecordRequest.isRecommended();
        User user = userRepository.findByUserSeq(userSeq).orElseThrow();
        ExerciseList exerciseList = exerciseListRepository.findByExerciseListSeq(exerciseListSeq);

        ExerciseRecord exerciseRecord = ExerciseRecord.builder()
                .exerciseStart(LocalDateTime.now())
                .exerciseReco(isRecommended)
                .user(user)
                .exerciseList(exerciseList)
                .build();

        exerciseRecordRepository.save(exerciseRecord);

        return 1;
    }

    @Transactional
    public String updateRecord(int userSeq, ExerciseRecordRequest exerciseRecordRequest){
        int avg = exerciseRecordRequest.getExerciseAvgBpm();
        int max = exerciseRecordRequest.getExerciseMaxBpm();
        Integer distance = exerciseRecordRequest.getDistance();
        exerciseRecordRepositoryCustom.updateExerciseRecord(userSeq, avg, max);
        // TODO : 심박수 기반으로 점수 받아서 유저 경험치에도 저장해야되고
        return null;
    }

    public List<ExerciseRecordResponse> selectAll(int userSeq){
        List<ExerciseRecordResponse> exerciseRecordResponseList = new ArrayList<>();

        User user = userRepository.findByUserSeq(userSeq).orElseThrow();

        List<ExerciseRecord> exerciseRecordList = exerciseRecordRepository.findAllByUserOrderByExerciseEndDesc(user).orElseThrow();

        for (ExerciseRecord exerciseRecord : exerciseRecordList){
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
