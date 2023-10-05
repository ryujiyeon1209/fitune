package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;

import java.util.List;

public interface ExerciseRecordRepositoryCustom{
    ExerciseRecord selectTodayRecord(User user);

    List<Integer> selectTodayRandom(User user);
}
