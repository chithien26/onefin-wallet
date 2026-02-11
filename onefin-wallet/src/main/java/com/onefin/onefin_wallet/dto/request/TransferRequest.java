package com.onefin.onefin_wallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransferRequest {
    String fromWallet;
    String toWallet;
    BigDecimal amount;
    String description;

}
