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
    CellResponse cell;
    PreferExerciseResponse preferExerciseResponse;
    List<ExerciseRecord> exerciseRecord;
    List<BattleRecord> battleRecord;
}
