package com.onefin.onefin_wallet.dto.response;

import com.onefin.onefin_wallet.EnumApp.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class TransferResponse {
    String code;
    String fromWallet;
    String toWallet;
    BigDecimal amount;
    String description;
    LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    TransactionStatus status;
}
