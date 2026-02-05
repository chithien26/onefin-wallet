package com.onefin.onefin_wallet.controller;

import com.onefin.onefin_wallet.dto.request.AuthenticationRequest;
import com.onefin.onefin_wallet.dto.response.ApiResponse;
import com.onefin.onefin_wallet.dto.response.AuthenticationResponse;
import com.onefin.onefin_wallet.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthenticationController {
    AuthService authenticationService;

    @PostMapping
    public ApiResponse<AuthenticationResponse> Authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
        return new ApiResponse<>(authenticationResponse.isResult() ? 200 : 201, authenticationResponse.isResult() ? "Success" : "Failed", authenticationResponse);
    }


}
