package com.fun.fitune.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseRecordRequest {
    Byte exerciseListSeq;
    boolean recommended;
    LocalDateTime exerciseStart;
    LocalDateTime exerciseEnd;
    int exerciseAvgBpm;
    int exerciseMaxBpm;
    int review;
    int weather;
}
