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

    public UserInfoResponse(User user){
        this.nickname=user.getNickname();
    }


}
