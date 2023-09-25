package com.fun.fitune.api.service;


import com.fun.fitune.api.dto.request.NicknameRequest;
import com.fun.fitune.api.dto.request.UserInfoRequest;
import com.fun.fitune.api.dto.response.UserSuperResponse;
import com.fun.fitune.db.domain.*;
import com.fun.fitune.db.repository.*;
import com.fun.fitune.exception.CustomException;
import com.fun.fitune.exception.CustomExceptionList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CellRepository cellRepository;
    private final PreferExerciseRepository preferExerciseRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final BattleRecordRepository battleRecordRepository;

    public UserSuperResponse selectAllUserInfo(int userSeq){
        User user = userRepository.findByUserSeq(userSeq).orElseThrow();
        Cell cell = cellRepository.findByUser(user).orElseThrow();
        List<PreferExercise> preferExerciseList = preferExerciseRepository.findAllByUser(user).orElseThrow();
        List<ExerciseRecord> exerciseRecordList = exerciseRecordRepository.findAllByUserOrderByExerciseEndDesc(user).orElseThrow();
        List<BattleRecord> battleRecordList = battleRecordRepository.findAllByBattleUserSeqOrderByBattleDateDesc(userSeq).orElseThrow();

        UserSuperResponse userSuperResponse = UserSuperResponse.builder()
                .user(user)
                .cell(cell)
                .preferExercise(preferExerciseList)
                .exerciseRecord(exerciseRecordList)
                .battleRecord(battleRecordList)
                .build();

        return userSuperResponse;
    }

    public User getUserInfo(Integer userSeq){
        return userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new CustomException(CustomExceptionList.USER_NOT_FOUND_ERROR));
    }

    @Transactional(readOnly = false)
    public String changeNickname(NicknameRequest nicknameReq) {
        User user = userRepository.findById(nicknameReq.getUserSeq())
                .orElseThrow(()-> new CustomException(CustomExceptionList.USER_NOT_FOUND_ERROR));

        user.setNickname(nicknameReq.getNickname());
        userRepository.save(user);

        return user.getNickname();
    }

    public User changeUserInfo(UserInfoRequest userInfoReq) {
        User user = userRepository.findById(userInfoReq.getUserSeq())
                .orElseThrow(()-> new CustomException(CustomExceptionList.USER_NOT_FOUND_ERROR));

        user.setAge(userInfoReq.getAge());
        user.setHeight(userInfoReq.getHeight());
        user.setWeight(userInfoReq.getWeight());

        return userRepository.save(user);
    }
}
