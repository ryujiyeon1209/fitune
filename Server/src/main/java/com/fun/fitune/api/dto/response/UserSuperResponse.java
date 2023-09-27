package com.fun.fitune.api.dto.response;

import com.fun.fitune.db.domain.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSuperResponse {
    User user;
    Cell cell;
    PreferExercise preferExercise;
    List<ExerciseRecord> exerciseRecord;
    List<BattleRecord> battleRecord;
}
