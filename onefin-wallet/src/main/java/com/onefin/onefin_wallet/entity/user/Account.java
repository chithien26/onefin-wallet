package com.onefin.onefin_wallet.entity.user;

import com.onefin.onefin_wallet.entity.base.BaseEntity;
import com.onefin.onefin_wallet.entity.wallet.MainWallet;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Account extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "owner_id")
    User owner;

    @OneToMany(mappedBy = "account")
    List<MainWallet> mainWallets;


}
