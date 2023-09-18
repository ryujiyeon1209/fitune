package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.ExerciseList;
import com.fun.fitune.db.domain.PreferExercise;
import com.fun.fitune.db.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreferExerciseRepository extends JpaRepository<PreferExercise, Integer> {
    Optional<List<PreferExercise>> findAllByUser(User user);

    void deleteByExerciseListAndUser(ExerciseList exerciseList, User user);
}
