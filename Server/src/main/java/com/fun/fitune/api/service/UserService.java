package com.fun.fitune.api.service;


import com.fun.fitune.api.dto.request.NicknameRequest;
import com.fun.fitune.api.dto.request.UserCreateRequest;
import com.fun.fitune.api.dto.request.UserInfoRequest;
import com.fun.fitune.api.dto.request.UserLoginRequest;
import com.fun.fitune.api.dto.response.PreferExerciseResponse;
import com.fun.fitune.api.dto.response.UserSuperResponse;
import com.fun.fitune.db.domain.*;
import com.fun.fitune.db.repository.*;
import com.fun.fitune.exception.CustomException;
import com.fun.fitune.exception.CustomExceptionList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CellRepository cellRepository;
    private final ExerciseListRepository exerciseListRepository;
    private final PreferExerciseRepository preferExerciseRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final BattleRecordRepository battleRecordRepository;

    public UserSuperResponse selectAllUserInfo(int userSeq) {
        User user = userRepository.findByUserSeq(userSeq).orElseThrow();
        Cell cell = cellRepository.findByUser(user).orElseThrow();
        PreferExercise preferExercise = preferExerciseRepository.findByUser(user).orElseThrow();
        List<ExerciseRecord> exerciseRecordList = exerciseRecordRepository.findAllByUserOrderByExerciseEndDesc(user).orElseThrow();
        List<BattleRecord> battleRecordList = battleRecordRepository.findAllByBattleUserSeqOrderByBattleDateDesc(userSeq).orElseThrow();

        UserSuperResponse userSuperResponse = UserSuperResponse.builder()
                .user(user)
                .cell(cell)
                .preferExerciseResponse(PreferExerciseResponse.builder()
                        .exerciseListSeq(preferExercise.getExerciseList().getExerciseListSeq())
                        .exerciseName(preferExercise.getExerciseList().getExerciseName())
                        .build())
                .exerciseRecord(exerciseRecordList)
                .battleRecord(battleRecordList)
                .build();

        return userSuperResponse;
    }

    public User getUserInfo(Integer userSeq) {
        return userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new CustomException(CustomExceptionList.USER_NOT_FOUND_ERROR));
    }

    @Transactional(readOnly = false)
    public String insertUser(UserCreateRequest userCreateRequest) {
        userRepository.save(User.builder()
                .email(userCreateRequest.getEmail())
                .password(userCreateRequest.getPassword())
                .nickname(userCreateRequest.getNickName())
                .age(LocalDateTime.now().getYear() - userCreateRequest.getYear())
                .height(userCreateRequest.getHeight())
                .weight(userCreateRequest.getWeight())
                .bodyFatPercentage(userCreateRequest.getBodyFatPer())
                .activeBPM(userCreateRequest.getActiveBpm())
                .RestingBPM(userCreateRequest.getRestingBpm())
                .build());

        System.out.println("왜 안됨");

        User user = userRepository.findByEmail(userCreateRequest.getEmail()).orElseThrow();

        ExerciseList exerciseList = exerciseListRepository.findByExerciseListSeq(userCreateRequest.getPreferExerciseSeq());

        preferExerciseRepository.save(
                PreferExercise.builder()
                        .user(user)
                        .exerciseList(exerciseList)
                        .build()
        );

        cellRepository.save(Cell.builder()
                .user(user)
                .cellName(userCreateRequest.getCellName())
                .build()
        );

        return "hi";
    }

    public Boolean selectUser(UserLoginRequest loginRequest){
        User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword()).orElseThrow();

        if (user != null) return true;
        else return false;
    }

    @Transactional(readOnly = false)
    public String changeNickname(NicknameRequest nicknameReq) {
        User user = userRepository.findById(nicknameReq.getUserSeq())
                .orElseThrow(() -> new CustomException(CustomExceptionList.USER_NOT_FOUND_ERROR));

        user.setNickname(nicknameReq.getNickname());
        userRepository.save(user);

        return user.getNickname();
    }

    public User changeUserInfo(UserInfoRequest userInfoReq) {
        User user = userRepository.findById(userInfoReq.getUserSeq())
                .orElseThrow(() -> new CustomException(CustomExceptionList.USER_NOT_FOUND_ERROR));

        user.setAge(userInfoReq.getAge());
        user.setHeight(userInfoReq.getHeight());
        user.setWeight(userInfoReq.getWeight());

        return userRepository.save(user);
    }

    public List<User> findRandomUsers(int userSeq){
        return userRepository.findRandomUsers(userSeq);
    }
}
