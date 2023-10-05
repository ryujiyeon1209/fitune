package com.fun.fitune.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class RecommendResponse {
    String recommendFirst;
    String recommendSecond;
    String recommendThird;

}
