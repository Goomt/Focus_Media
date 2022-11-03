package com.example.demo.exception;

import lombok.Getter;

public enum ExceptionCode {
    EMAIL_NOT_FOUND(404, "Email Not Found"),
    EMAIL_EXIST(409, "Email Exists"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_EXIST(409, "Member Exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
