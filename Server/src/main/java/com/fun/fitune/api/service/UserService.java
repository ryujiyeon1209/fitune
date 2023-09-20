package com.fun.fitune.api.service;


import com.fun.fitune.api.dto.request.NicknameRequest;
import com.fun.fitune.api.dto.request.UserInfoRequest;
import com.fun.fitune.api.dto.response.UserInfoResponse;
import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.UserRepository;
import com.fun.fitune.exception.CustomException;
import com.fun.fitune.exception.CustomExceptionList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

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
