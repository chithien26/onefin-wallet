package com.onefin.onefin_wallet.entity.base;

import com.onefin.onefin_wallet.entity.user.Account;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    Account account;
}
