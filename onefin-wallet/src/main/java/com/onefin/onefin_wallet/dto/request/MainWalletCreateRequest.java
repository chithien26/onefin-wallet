package com.onefin.onefin_wallet.dto.request;

import com.onefin.onefin_wallet.entity.user.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MainWalletCreateRequest {
    String walletNumber;
    BigDecimal balance = BigDecimal.ZERO;
    String currency = "VNĐ";
    Account account;
}
