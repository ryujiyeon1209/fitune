package com.fun.fitune.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NicknameRequest {
    int userSeq;
    String nickname;
}
