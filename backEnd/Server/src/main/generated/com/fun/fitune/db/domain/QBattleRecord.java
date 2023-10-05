package com.fun.fitune.db.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBattleRecord is a Querydsl query type for BattleRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBattleRecord extends EntityPathBase<BattleRecord> {

    private static final long serialVersionUID = -1918833286L;

    public static final QBattleRecord battleRecord = new QBattleRecord("battleRecord");

    public final DateTimePath<java.time.LocalDateTime> battleDate = createDateTime("battleDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> battleOtherSeq = createNumber("battleOtherSeq", Integer.class);

    public final NumberPath<Integer> battleRecordSeq = createNumber("battleRecordSeq", Integer.class);

    public final NumberPath<Integer> battleUserSeq = createNumber("battleUserSeq", Integer.class);

    public final NumberPath<Integer> winnerSeq = createNumber("winnerSeq", Integer.class);

    public QBattleRecord(String variable) {
        super(BattleRecord.class, forVariable(variable));
    }

    public QBattleRecord(Path<? extends BattleRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBattleRecord(PathMetadata metadata) {
        super(BattleRecord.class, metadata);
    }

}

