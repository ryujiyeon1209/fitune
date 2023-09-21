package com.fun.fitune.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseRecordRequest {
    Byte exerciseListSeq;
    boolean recommended;
    int exerciseAvgBpm;
    int exerciseMaxBpm;
    int review;
}
