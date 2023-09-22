package com.fun.fitune.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseListResponse {
    private Byte exerciseListSeq;
    private String exerciseListName;
}
