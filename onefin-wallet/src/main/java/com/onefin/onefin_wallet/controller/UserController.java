package com.onefin.onefin_wallet.controller;

import com.onefin.onefin_wallet.dto.request.UserCreateRequest;
import com.onefin.onefin_wallet.dto.response.ApiResponse;
import com.onefin.onefin_wallet.dto.response.UserCreateResponse;
import com.onefin.onefin_wallet.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<UserCreateResponse>> getAllUsers() {
        return new ApiResponse<>(200, "Success", userService.findAllUsers());
    }

    @GetMapping("/user")
    public ApiResponse<UserCreateResponse> getUserByEmail(@RequestParam String email) {
        return new ApiResponse<>(200, "Success", userService.findByEmail(email));
    }

    @PostMapping("/user")
    public ApiResponse<UserCreateResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return new ApiResponse<>(201, "User Created", userService.createUser(userCreateRequest));
    }
}
