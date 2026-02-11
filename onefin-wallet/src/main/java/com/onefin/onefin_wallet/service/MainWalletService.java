package com.onefin.onefin_wallet.service;

import com.onefin.onefin_wallet.dto.request.MainWalletCreateRequest;
import com.onefin.onefin_wallet.entity.wallet.MainWallet;
import com.onefin.onefin_wallet.reposotory.MainWalletRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainWalletService {

    MainWalletRepository mainWalletRepository;

    public MainWallet createMainWallet(MainWalletCreateRequest mainWalletCreateRequest) {
        MainWallet mainWallet = MainWallet.builder()
                .walletNumber(mainWalletCreateRequest.getWalletNumber())
                .balance(mainWalletCreateRequest.getBalance())
                .account(mainWalletCreateRequest.getAccount())
                .currency(mainWalletCreateRequest.getCurrency())
                .build();
        return mainWalletRepository.save(mainWallet);
    }

}
