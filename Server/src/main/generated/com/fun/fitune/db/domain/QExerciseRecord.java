package com.fun.fitune.db.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseRecord is a Querydsl query type for ExerciseRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseRecord extends EntityPathBase<ExerciseRecord> {

    private static final long serialVersionUID = 540407386L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseRecord exerciseRecord = new QExerciseRecord("exerciseRecord");

    public final NumberPath<Integer> exerciseAvgBpm = createNumber("exerciseAvgBpm", Integer.class);

    public final NumberPath<Integer> exerciseDistance = createNumber("exerciseDistance", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> exerciseEnd = createDateTime("exerciseEnd", java.time.LocalDateTime.class);

    public final QExerciseList exerciseList;

    public final NumberPath<Integer> exerciseMaxBpm = createNumber("exerciseMaxBpm", Integer.class);

    public final BooleanPath exerciseReco = createBoolean("exerciseReco");

    public final NumberPath<Integer> exerciseRecordSeq = createNumber("exerciseRecordSeq", Integer.class);

    public final NumberPath<Integer> exerciseReview = createNumber("exerciseReview", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> exerciseStart = createDateTime("exerciseStart", java.time.LocalDateTime.class);

    public final NumberPath<Integer> exerciseWeather = createNumber("exerciseWeather", Integer.class);

    public final QUser user;

    public QExerciseRecord(String variable) {
        this(ExerciseRecord.class, forVariable(variable), INITS);
    }

    public QExerciseRecord(Path<? extends ExerciseRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseRecord(PathMetadata metadata, PathInits inits) {
        this(ExerciseRecord.class, metadata, inits);
    }

    public QExerciseRecord(Class<? extends ExerciseRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exerciseList = inits.isInitialized("exerciseList") ? new QExerciseList(forProperty("exerciseList")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

