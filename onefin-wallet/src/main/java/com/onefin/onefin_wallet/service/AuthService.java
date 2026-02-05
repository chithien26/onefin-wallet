package com.onefin.onefin_wallet.service;

import com.onefin.onefin_wallet.dto.request.AuthenticationRequest;
import com.onefin.onefin_wallet.dto.response.AuthenticationResponse;
import com.onefin.onefin_wallet.exception.AppException;
import com.onefin.onefin_wallet.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthService {
    UserService userService;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!userService.existsByUsername(authenticationRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        boolean result = passwordEncoder.matches(authenticationRequest.getPassword(),
                userService.findByUsername(authenticationRequest.getUsername()).getPassword());
        return new AuthenticationResponse(result, result ? "123" : "");
    }
}
