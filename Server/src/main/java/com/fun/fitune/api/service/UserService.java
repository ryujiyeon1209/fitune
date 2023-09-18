package com.fun.fitune.api.service;


import com.fun.fitune.db.domain.User;
import com.fun.fitune.db.repository.UserRepository;
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
                .orElseThrow();
    }
}
