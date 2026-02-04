package com.onefin.onefin_wallet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    int code;
    String message;
    T data = null;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
