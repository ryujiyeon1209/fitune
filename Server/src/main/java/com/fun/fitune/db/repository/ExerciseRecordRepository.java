package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Integer>, ExerciseRecordRepositoryCustom {
    Optional<List<ExerciseRecord>> findAllByUserOrderByExerciseEndDesc(User user);
}
