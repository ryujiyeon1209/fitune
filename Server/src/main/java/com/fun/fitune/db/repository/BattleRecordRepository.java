package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.BattleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BattleRecordRepository extends JpaRepository<BattleRecord, Integer> {
    Optional<List<BattleRecord>> findAllByBattleUserSeqOrderByBattleDateDesc(int userSeq);
}
