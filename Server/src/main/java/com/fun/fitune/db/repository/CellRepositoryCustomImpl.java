package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.Cell;
import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.fun.fitune.db.domain.QCell.cell;

public class CellRepositoryCustomImpl extends QuerydslRepositorySupport implements CellRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public CellRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Cell.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public void increaseCellExp(User user, int cellExp) {
        queryFactory.update(cell)
                .set(cell.cellExp, cell.cellExp.add(cellExp))
                .where(cell.user.eq(user))
                .execute();
    }
}
