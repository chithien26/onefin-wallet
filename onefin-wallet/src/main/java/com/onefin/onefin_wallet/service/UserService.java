package com.onefin.onefin_wallet.service;

import com.onefin.onefin_wallet.dto.request.MainWalletCreateRequest;
import com.onefin.onefin_wallet.dto.request.UserCreateRequest;
import com.onefin.onefin_wallet.dto.response.UserCreateResponse;
import com.onefin.onefin_wallet.entity.user.Account;
import com.onefin.onefin_wallet.entity.user.User;
import com.onefin.onefin_wallet.entity.wallet.MainWallet;
import com.onefin.onefin_wallet.mapper.UserMapper;
import com.onefin.onefin_wallet.reposotory.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    AccountService accountService;
    MainWalletService mainWalletService;


    public List<UserCreateResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserCreateResponse)
                .toList();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        User user = userMapper.toUser(userCreateRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Account account = accountService.createAccount(user);

        MainWallet defaultmainWallet = mainWalletService.createMainWallet(
                MainWalletCreateRequest.builder()
                        .walletNumber(userCreateRequest.getDefaultWalletNumber())
                        .account(account)
                        .build());

        user.setAccount(account);

        userRepository.save(user);
        return userMapper.toUserCreateResponse(user);
    }
}
