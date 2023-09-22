package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.Cell;
import com.fun.fitune.db.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CellRepository extends JpaRepository<Cell, Integer> {
    Optional<Cell> findByUser(User user);
}
