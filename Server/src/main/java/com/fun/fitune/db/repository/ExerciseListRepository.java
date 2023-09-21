package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.ExerciseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseListRepository extends JpaRepository<ExerciseList, Byte> {
    public ExerciseList findByExerciseListSeq(Byte exerciseList);
}
