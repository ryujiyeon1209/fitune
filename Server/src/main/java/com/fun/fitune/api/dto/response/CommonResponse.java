package com.fun.fitune.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {
    private String message;
    private T data;

    @Builder
    public CommonResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public CommonResponse() {

    }
}
