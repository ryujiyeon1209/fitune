package com.fun.fitune.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final com.fun.fitune.exception.CustomExceptionList exception;

    public CustomException(com.fun.fitune.exception.CustomExceptionList e) {
        super(e.getMessage());
        this.exception = e;
    }
//    private final String message;
//    public CustomException(String message) {
//        super(message);
//        this.message = message;
//    }

}