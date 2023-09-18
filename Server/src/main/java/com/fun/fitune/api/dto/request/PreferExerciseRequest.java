package com.fun.fitune.api.dto.request;

import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.domain.PreferExercise;
import com.fun.fitune.db.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferExerciseRequest {
    private Integer userSeq;
    private List<Byte> exerciseListSeq;
}
