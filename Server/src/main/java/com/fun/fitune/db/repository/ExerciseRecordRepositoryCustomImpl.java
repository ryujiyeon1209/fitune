package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.fun.fitune.db.domain.QExerciseRecord.exerciseRecord;

@Slf4j
@Repository
public class ExerciseRecordRepositoryCustomImpl extends QuerydslRepositorySupport implements ExerciseRecordRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ExerciseRecordRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(ExerciseRecord.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public ExerciseRecord selectTodayRecord(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime today = LocalDateTime.of(
                now.getYear(),
                now.getMonth(),
                now.getDayOfMonth(),
                0,
                0,
                0
        );

        ExerciseRecord record = queryFactory
                .selectFrom(exerciseRecord)
                .where(exerciseRecord.user.eq(user), exerciseRecord.exerciseEnd.after(today))
                .fetchOne();

        return record;
    }

    @Override
    public List<Integer> selectTodayRandom(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime today = LocalDateTime.of(
                now.getYear(),
                now.getMonth(),
                now.getDayOfMonth(),
                0,
                0,
                0
        );

        List<Integer> records = queryFactory
                .select(exerciseRecord.user.userSeq)
                .from(exerciseRecord)
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .where(exerciseRecord.user.ne(user))
                .limit(5)
                .fetch();

        return records;
    }
}
