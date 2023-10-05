package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends JpaRepository<Cell, Integer> {
}
