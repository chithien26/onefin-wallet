package com.onefin.onefin_wallet.entity.user;

import com.onefin.onefin_wallet.entity.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    String username;
    String password;
    @Column(unique = true)
    String email;
    @Column(unique = true, nullable = true)
    String phone;
    @Column(nullable = true)
    String address;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    Account account;
}
