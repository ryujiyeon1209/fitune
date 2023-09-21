package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.BattleRecordRequest;
import com.fun.fitune.api.dto.response.BattleRecordResponse;
import com.fun.fitune.db.domain.BattleRecord;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.BattleRecordRepository;
import com.fun.fitune.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BattleRecordService {
    private final BattleRecordRepository battleRecordRepository;
    private final UserRepository userRepository;

    // TODO : 오늘 운동을 한 사람 중 랜덤매칭
    @Transactional
    public String insertBattleRecord(BattleRecordRequest battleRecordRequest) {
        int winnerSeq = Battle(battleRecordRequest.getUserSeq(), battleRecordRequest.getOtherSeq());

        BattleRecord battleRecord = BattleRecord.builder()
                .battleDate(LocalDateTime.now())
                .battleUserSeq(battleRecordRequest.getUserSeq())
                .battleOtherSeq(battleRecordRequest.getOtherSeq())
                .winnerSeq(winnerSeq)
                .build();

        battleRecordRepository.save(battleRecord);

        return userRepository.findByUserSeq(winnerSeq).orElseThrow().getNickname();
    }

    // TODO : 운동기록 API 완성되면 그 날 심박수 알고리즘으로 점수 변환 후 대결
    private int Battle(int userSeq, int otherSeq) {
        if (userSeq > otherSeq) return userSeq;
        else return otherSeq;
    }

    // 대결 기록 보기
    public List<BattleRecordResponse> selectAll(int userSeq) {
        List<BattleRecordResponse> battleRecordResponses = new ArrayList<>();

        List<BattleRecord> battleRecords = battleRecordRepository
                .findAllByBattleUserSeqOrderByBattleDateDesc(userSeq)
                .orElseThrow();

        for (BattleRecord battleRecord : battleRecords){
            String winnerName = userRepository
                    .findByUserSeq(battleRecord.getWinnerSeq()).orElseThrow()
                    .getNickname();

            User battleOther = userRepository
                    .findByUserSeq(battleRecord.getBattleOtherSeq()).orElseThrow();

            BattleRecordResponse battleRecordResponse = BattleRecordResponse.builder()
                    .battleRecordSeq(battleRecord.getBattleRecordSeq())
                    .battleDate(battleRecord.getBattleDate())
                    .winnerName(winnerName)
                    .battleOtherSeq(battleOther.getUserSeq())
                    .battleOtherName(battleOther.getNickname())
                    .build();

            battleRecordResponses.add(battleRecordResponse);
        }

        return battleRecordResponses;
    }
}
