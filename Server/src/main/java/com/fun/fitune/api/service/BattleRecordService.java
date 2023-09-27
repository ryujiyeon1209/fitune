package com.fun.fitune.api.service;

import com.fun.fitune.api.dto.request.BattleRecordRequest;
import com.fun.fitune.api.dto.response.BattleOpponentResponse;
import com.fun.fitune.api.dto.response.BattleRecordResponse;
import com.fun.fitune.db.domain.BattleRecord;
import com.fun.fitune.db.domain.Cell;
import com.fun.fitune.db.domain.ExerciseRecord;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BattleRecordService {
    private final BattleRecordRepository battleRecordRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final UserRepository userRepository;
    private final CellRepository cellRepository;

    // TODO : 오늘 운동을 한 사람 중 랜덤매칭
    @Transactional
    public String insertBattleRecord(BattleRecordRequest battleRecordRequest) {
        int winnerSeq = battle(battleRecordRequest.getUserSeq(), battleRecordRequest.getOtherSeq());

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
    private int battle(int userSeq, int otherSeq) {
        User user = userRepository.findByUserSeq(userSeq).orElseThrow();
        User other = userRepository.findByUserSeq(otherSeq).orElseThrow();

        ExerciseRecord todayUserRecord = exerciseRecordRepository.selectTodayRecord(user);
        ExerciseRecord todayOtherRecord = exerciseRecordRepository.selectTodayRecord(other);

        int userScore = cellRepository.findByUser(user).orElseThrow().getCellLatestExp();
        int otherScore = cellRepository.findByUser(user).orElseThrow().getCellLatestExp();

        if (userScore > otherScore) return userSeq;
        else if (otherScore > userScore) return otherSeq;
        else {
            if (todayOtherRecord.getExerciseMaxBpm() - other.getRestingBPM()
                    > todayUserRecord.getExerciseMaxBpm() - user.getRestingBPM())
                return otherSeq;
            else return userSeq;
        }
    }

    // 대결 기록 보기
    public List<BattleRecordResponse> selectAll(int userSeq) {
        List<BattleRecordResponse> battleRecordResponses = new ArrayList<>();

        List<BattleRecord> battleRecords = battleRecordRepository
                .findAllByBattleUserSeqOrderByBattleDateDesc(userSeq)
                .orElseThrow();

        for (BattleRecord battleRecord : battleRecords) {
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

    //TODO : 오늘 운동한 사람 중 userSeq를 제외한 5명을 추출하는 서비스 로직 짜야됨
    public List<BattleOpponentResponse> selectOpponent(int userSeq) {
        User user = User.builder().userSeq(userSeq).build();

        List<BattleOpponentResponse> opponents = new ArrayList<>();

        List<Integer> opponentSeqs = exerciseRecordRepository.selectTodayRandom(user);

        for (Integer opponentSeq : opponentSeqs) {
            User other = userRepository.findByUserSeq(opponentSeq).orElseThrow();

            Cell cell = cellRepository.findByUser(other).orElseThrow();

            BattleOpponentResponse.builder()
                    .userSeq(other.getUserSeq())
                    .cellExp(cell.getCellExp())
                    .cellName(cell.getCellName())
                    .height(other.getHeight())
                    .weight(other.getWeight())
                    .bpm(other.getRestingBPM())
                    .build();
        }

        return opponents;
    }
}
