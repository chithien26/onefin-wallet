package com.onefin.onefin_wallet.EnumApp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionStatus {
    PENDING("Đang xử lý"),
    COMPLETED("Thành công"),
    FAILED("Thất bại"),
    CANCELLED("Hủy bỏ");
    private final String description;
}
