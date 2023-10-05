package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.User;

public interface CellRepositoryCustom {
    void increaseCellExp(User user, int cellExp);
}
