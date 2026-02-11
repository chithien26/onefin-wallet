package com.onefin.onefin_wallet.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    String fromWallet;
    @NotNull
    String toWallet;
    @Min(value = 2000, message = "Amount must be at least 2000")
    BigDecimal amount;
    String description;

    @AssertTrue(message = "From and To wallet must be different")
    public boolean isDifferentAccount() {
        return !fromWallet.equals(toWallet);
    }
}
