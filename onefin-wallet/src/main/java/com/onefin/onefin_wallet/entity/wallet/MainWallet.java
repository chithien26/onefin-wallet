package com.onefin.onefin_wallet.entity.wallet;

import com.onefin.onefin_wallet.entity.base.BaseWallet;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class MainWallet extends BaseWallet {

}
