package com.fun.fitune.db.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExerciseList is a Querydsl query type for ExerciseList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseList extends EntityPathBase<ExerciseList> {

    private static final long serialVersionUID = -245421849L;

    public static final QExerciseList exerciseList = new QExerciseList("exerciseList");

    public final NumberPath<Byte> exerciseListSeq = createNumber("exerciseListSeq", Byte.class);

    public final StringPath exerciseName = createString("exerciseName");

    public QExerciseList(String variable) {
        super(ExerciseList.class, forVariable(variable));
    }

    public QExerciseList(Path<? extends ExerciseList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExerciseList(PathMetadata metadata) {
        super(ExerciseList.class, metadata);
    }

}

