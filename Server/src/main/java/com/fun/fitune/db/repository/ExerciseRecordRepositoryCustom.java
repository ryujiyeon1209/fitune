package com.fun.fitune.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordRepositoryCustom{
    void updateExerciseRecord(int userSeq, int avg, int max);
}
