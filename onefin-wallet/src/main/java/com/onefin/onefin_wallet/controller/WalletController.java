package com.onefin.onefin_wallet.controller;

import com.onefin.onefin_wallet.dto.request.CreditRequest;
import com.onefin.onefin_wallet.dto.request.TransferRequest;
import com.onefin.onefin_wallet.dto.response.ApiResponse;
import com.onefin.onefin_wallet.dto.response.TransferResponse;
import com.onefin.onefin_wallet.service.MainWalletService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WalletController {
    MainWalletService mainWalletService;

    @PostMapping("/wallet")
    public void creditWallet(@RequestBody CreditRequest creditRequest) {
        mainWalletService.credit(creditRequest.getMainWalletNumber(), creditRequest.getAmount());
    }

    @PostMapping("/wallet/transfer")
    public ApiResponse<TransferResponse> transferBetweenWallets(@RequestBody TransferRequest transferRequest) {
        TransferResponse transferResponse = mainWalletService.transfer(transferRequest);
        return new ApiResponse<>(200, "Transfer Successful", transferResponse);
    }
}
