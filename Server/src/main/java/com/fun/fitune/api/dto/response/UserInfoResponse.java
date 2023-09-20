package com.fun.fitune.api.dto.response;

import com.fun.fitune.db.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoResponse {
    String nickname;
    int userSeq;
    int age;
    int height;
    int weight;

    public UserInfoResponse(User user){
        this.nickname=user.getNickname();
        this.userSeq=user.getUserSeq();
        this.age=user.getAge();
        this.height=user.getHeight();
        this.weight=user.getWeight();
    }
}
