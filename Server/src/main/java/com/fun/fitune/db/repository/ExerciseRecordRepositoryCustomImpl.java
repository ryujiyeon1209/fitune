package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.fun.fitune.db.domain.QExerciseRecord.exerciseRecord;

@Slf4j
@Repository
public class ExerciseRecordRepositoryCustomImpl extends QuerydslRepositorySupport implements ExerciseRecordRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public ExerciseRecordRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        super(ExerciseRecord.class);
        this.entityManager = entityManager;
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public void updateExerciseRecord(int userSeq, int avg, int max) {
        User user = User.builder()
                .userSeq(userSeq)
                .build();

        ExerciseRecord recentRecord = queryFactory
                .selectFrom(exerciseRecord)
                .where(exerciseRecord.user.eq(user))
                .orderBy(exerciseRecord.exerciseStart.desc())
                .fetchFirst();

        recentRecord.setExerciseAvgBpm(avg);
        recentRecord.setExerciseMaxBpm(max);
        recentRecord.setExerciseEnd(LocalDateTime.now());

        entityManager.persist(recentRecord);
    }

    @Override
    public void updateExerciseReview(int userSeq, int review) {
        User user = User.builder()
                .userSeq(userSeq)
                .build();

        ExerciseRecord recentRecord = queryFactory
                .selectFrom(exerciseRecord)
                .where(exerciseRecord.user.eq(user))
                .orderBy(exerciseRecord.exerciseStart.desc())
                .fetchFirst();

        recentRecord.setExerciseReview(review);

        entityManager.persist(recentRecord);
    }
}
