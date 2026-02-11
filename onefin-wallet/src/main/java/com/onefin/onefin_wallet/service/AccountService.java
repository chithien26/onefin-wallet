package com.onefin.onefin_wallet.service;

import com.onefin.onefin_wallet.entity.user.Account;
import com.onefin.onefin_wallet.entity.user.User;
import com.onefin.onefin_wallet.reposotory.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;

    @Transactional
    public Account createAccount(User user) {
        Account account = Account.builder()
                .owner(user)
                .build();
        return accountRepository.save(account);
    }

}
