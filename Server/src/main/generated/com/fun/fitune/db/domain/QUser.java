package com.fun.fitune.db.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -809665764L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> activeBPM = createNumber("activeBPM", Integer.class);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Integer> bodyFatPercentage = createNumber("bodyFatPercentage", Integer.class);

    public final NumberPath<Integer> bpm = createNumber("bpm", Integer.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> userSeq = createNumber("userSeq", Integer.class);

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

