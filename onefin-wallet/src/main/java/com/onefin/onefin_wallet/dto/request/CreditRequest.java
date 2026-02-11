package com.onefin.onefin_wallet.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditRequest {
    private String mainWalletNumber;
    private BigDecimal amount;
}
