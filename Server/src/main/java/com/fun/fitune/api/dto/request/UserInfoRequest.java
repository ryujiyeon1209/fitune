package com.fun.fitune.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {
    int userSeq;
    private String email;
    private String password;
    private String nickName;
    private int year;
    private int height;
    private int weight;
    private Integer bodyFatPer;
    private int restingBpm;
    private int activeBpm;
    private int preferExerciseSeq;

    public UserInfoRequest(String email, String password, String nickName, int year, int height, int weight, int bodyFatPer, int restingBpm, int activeBpm, int preferExerciseSeq) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.year = year;
        this.height = height;
        this.weight = weight;
        this.bodyFatPer = bodyFatPer;
        this.restingBpm = restingBpm;
        this.activeBpm = activeBpm;
        this.preferExerciseSeq = preferExerciseSeq;
    }

    public UserInfoRequest(int userSeq, String nickName, int height, int weight, int bodyFatPer) {
        this.userSeq = userSeq;
        this.nickName = nickName;
        this.height = height;
        this.weight = weight;
        this.bodyFatPer = bodyFatPer;
    }
}
