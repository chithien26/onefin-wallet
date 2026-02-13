package com.onefin.onefin_wallet.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.onefin.onefin_wallet.dto.request.AuthenticationRequest;
import com.onefin.onefin_wallet.dto.response.AuthenticationResponse;
import com.onefin.onefin_wallet.entity.user.User;
import com.onefin.onefin_wallet.exception.AppException;
import com.onefin.onefin_wallet.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AuthenticationService {
    UserService userService;

    protected String SIGNER_KEY = "e331a78798d631d65151bcb13bde7180dcfe38ffc570e9d6c8fdef8641147ab8";

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("onefin.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("userId", user.getId())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Error generate token", e);
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!userService.existsByUsername(authenticationRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        boolean result = passwordEncoder.matches(authenticationRequest.getPassword(),
                userService.findByUsername(authenticationRequest.getUsername()).getPassword());
        String token = generateToken(
                userService.findByUsername(authenticationRequest.getUsername())
        );
        return new AuthenticationResponse(result, result ? token : "");
    }
}
