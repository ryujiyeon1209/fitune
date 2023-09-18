package com.fun.fitune.api.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {
    int age;
    int height;
    int weight;
}
