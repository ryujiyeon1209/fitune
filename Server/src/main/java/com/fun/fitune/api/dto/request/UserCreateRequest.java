package com.fun.fitune.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {
    private String email;
    private String password;
    private String nickName;
    private int year;
    private int height;
    private int weight;
    private Integer bodyFatPer;
    private int restingBpm;
    private int activeBpm;
    private Byte preferExerciseSeq;
    private String cellName;
}
