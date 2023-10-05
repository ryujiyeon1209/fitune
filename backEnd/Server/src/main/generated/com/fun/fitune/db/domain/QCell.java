package com.fun.fitune.db.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCell is a Querydsl query type for Cell
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCell extends EntityPathBase<Cell> {

    private static final long serialVersionUID = -810215245L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCell cell = new QCell("cell");

    public final NumberPath<Long> cellExp = createNumber("cellExp", Long.class);

    public final NumberPath<Integer> cellLatestExp = createNumber("cellLatestExp", Integer.class);

    public final StringPath cellName = createString("cellName");

    public final NumberPath<Integer> cellSeq = createNumber("cellSeq", Integer.class);

    public final QUser user;

    public QCell(String variable) {
        this(Cell.class, forVariable(variable), INITS);
    }

    public QCell(Path<? extends Cell> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCell(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCell(PathMetadata metadata, PathInits inits) {
        this(Cell.class, metadata, inits);
    }

    public QCell(Class<? extends Cell> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

