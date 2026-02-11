package com.onefin.onefin_wallet.service;

import com.onefin.onefin_wallet.EnumApp.TransactionStatus;
import com.onefin.onefin_wallet.dto.request.MainWalletCreateRequest;
import com.onefin.onefin_wallet.dto.request.TransferRequest;
import com.onefin.onefin_wallet.dto.response.TransferResponse;
import com.onefin.onefin_wallet.entity.Transaction;
import com.onefin.onefin_wallet.entity.wallet.MainWallet;
import com.onefin.onefin_wallet.reposotory.MainWalletRepository;
import com.onefin.onefin_wallet.reposotory.TransactionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainWalletService {

    MainWalletRepository mainWalletRepository;
    TransactionRepository transactionRepository;

    @Transactional
    public MainWallet createMainWallet(MainWalletCreateRequest mainWalletCreateRequest) {
        if (mainWalletRepository.existsByWalletNumber(mainWalletCreateRequest.getWalletNumber())) {
            throw new RuntimeException("Wallet number already exists");
        }
        MainWallet mainWallet = MainWallet.builder()
                .walletNumber(mainWalletCreateRequest.getWalletNumber())
                .balance(mainWalletCreateRequest.getBalance())
                .account(mainWalletCreateRequest.getAccount())
                .currency(mainWalletCreateRequest.getCurrency())
                .build();
        return mainWalletRepository.save(mainWallet);
    }

    public void debit(String mainWalletNumber, BigDecimal amount) {
        MainWallet wallet = mainWalletRepository.findByWalletNumber(mainWalletNumber);
        wallet.setBalance(wallet.getBalance().subtract(amount));
        mainWalletRepository.save(wallet);
    }

    public void credit(String mainWalletNumber, BigDecimal amount) {
        MainWallet wallet = mainWalletRepository.findByWalletNumber(mainWalletNumber);
        wallet.setBalance(wallet.getBalance().add(amount));
        mainWalletRepository.save(wallet);
    }

    @Transactional
    public TransferResponse transfer(TransferRequest transferRequest) {

        String fromWallet = transferRequest.getFromWallet();
        String toWallet = transferRequest.getToWallet();
        BigDecimal amount = transferRequest.getAmount();

        Transaction transaction = Transaction.builder()
                .fromWallet(transferRequest.getFromWallet())
                .toWallet(transferRequest.getToWallet())
                .amount(transferRequest.getAmount())
                .description(transferRequest.getDescription())
                .build();

        try {
            debit(fromWallet, amount);
            credit(toWallet, amount);
            transaction.setStatus(TransactionStatus.COMPLETED);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new RuntimeException("Transaction failed: " + e.getMessage());
        }

        return TransferResponse.builder()
                .code(transaction.getCode())
                .fromWallet(transaction.getFromWallet())
                .toWallet(transaction.getToWallet())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .timestamp(transaction.getCreateTime())
                .status(transaction.getStatus())
                .build();
    }


}
