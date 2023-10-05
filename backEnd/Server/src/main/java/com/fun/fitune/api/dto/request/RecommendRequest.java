package com.fun.fitune.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendRequest {
    int userSeq;
    private int age;
    private int height;
    private int weight;
    private int body_fat_percentage;
    private int Resting_BPM;
    private int active_BPM;
}
