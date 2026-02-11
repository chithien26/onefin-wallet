package com.onefin.onefin_wallet.entity.base;

import com.onefin.onefin_wallet.entity.user.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@MappedSuperclass
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseWallet extends BaseEntity {
    String walletNumber;
    BigDecimal balance;
    String currency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    Account account;

    @PrePersist
    public void prePersist() {
        if (this.balance == null) {
            this.balance = BigDecimal.ZERO;
        }
        if (this.currency == null) {
            this.currency = "VNĐ";
        }
    }
}
