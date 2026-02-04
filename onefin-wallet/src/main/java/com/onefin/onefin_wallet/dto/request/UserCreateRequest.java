package com.onefin.onefin_wallet.dto.request;

import com.onefin.onefin_wallet.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserCreateRequest extends BaseEntity {
    String username;
    String password;
    String email;
    String phone;
    String address;
}
