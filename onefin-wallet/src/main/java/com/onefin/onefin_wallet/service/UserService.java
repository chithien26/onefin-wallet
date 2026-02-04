package com.onefin.onefin_wallet.service;

import com.onefin.onefin_wallet.dto.request.UserCreateRequest;
import com.onefin.onefin_wallet.dto.response.UserCreateResponse;
import com.onefin.onefin_wallet.entity.User;
import com.onefin.onefin_wallet.mapper.UserMapper;
import com.onefin.onefin_wallet.reposotory.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;


    public List<UserCreateResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserCreateResponse)
                .toList();
    }

    public UserCreateResponse findByEmail(String email) {
        return userMapper.toUserCreateResponse(userRepository.findByEmail(email));
    }

    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        User user = userMapper.toUser(userCreateRequest);
        userRepository.save(user);
        return userMapper.toUserCreateResponse(user);
    }
}
