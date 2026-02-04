package com.onefin.onefin_wallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
}
