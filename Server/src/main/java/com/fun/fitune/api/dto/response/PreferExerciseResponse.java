package com.fun.fitune.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferExerciseResponse {
    private Byte exerciseListSeq;
    private String exerciseName;
}
