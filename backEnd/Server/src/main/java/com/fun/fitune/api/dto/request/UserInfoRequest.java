package com.fun.fitune.api.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {
    int userSeq;
    private int age;
    private int height;
    private int weight;
}
