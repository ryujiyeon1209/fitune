package com.fun.fitune.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ExerciseRecordResponse {
    String exerciseName;
    boolean isRecommended;
    LocalDateTime exerciseStart;
    LocalDateTime exerciseEnd;
    int avgBpm;
    int maxBpm;
    int review;

    public ExerciseRecordResponse(String exerciseName, LocalDateTime exerciseStart, boolean isRecommended){
        this.exerciseName = exerciseName;
        this.exerciseStart = exerciseStart;
        this.isRecommended = isRecommended;
    }

    public ExerciseRecordResponse(LocalDateTime exerciseEnd, int avgBpm, int maxBpm) {
        this.exerciseEnd = exerciseEnd;
        this.avgBpm = avgBpm;
        this.maxBpm = maxBpm;
    }

    public ExerciseRecordResponse(int review) {
        this.review = review;
    }
}
