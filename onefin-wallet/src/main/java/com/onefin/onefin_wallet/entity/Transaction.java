package com.onefin.onefin_wallet.entity;

import com.onefin.onefin_wallet.EnumApp.TransactionStatus;
import com.onefin.onefin_wallet.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Transaction extends BaseEntity {
    String code;
    String description;
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    TransactionStatus status;
    
    @PrePersist
    public void generateCode() {
        this.code = "TXN-" + System.currentTimeMillis();
    }
}
