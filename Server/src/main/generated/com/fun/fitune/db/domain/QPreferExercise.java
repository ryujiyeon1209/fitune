package com.fun.fitune.db.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPreferExercise is a Querydsl query type for PreferExercise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPreferExercise extends EntityPathBase<PreferExercise> {

    private static final long serialVersionUID = -978229735L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPreferExercise preferExercise = new QPreferExercise("preferExercise");

    public final QExerciseList exerciseList;

    public final NumberPath<Integer> preferExerciseSeq = createNumber("preferExerciseSeq", Integer.class);

    public final QUser user;

    public QPreferExercise(String variable) {
        this(PreferExercise.class, forVariable(variable), INITS);
    }

    public QPreferExercise(Path<? extends PreferExercise> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPreferExercise(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPreferExercise(PathMetadata metadata, PathInits inits) {
        this(PreferExercise.class, metadata, inits);
    }

    public QPreferExercise(Class<? extends PreferExercise> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exerciseList = inits.isInitialized("exerciseList") ? new QExerciseList(forProperty("exerciseList")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

