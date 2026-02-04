package com.onefin.onefin_wallet.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_AGE(100, "Age must be between 18 and 65"),
    DUPLICATE_USERNAME(101, "Username already exists");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
