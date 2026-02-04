package com.onefin.onefin_wallet.mapper;

import com.onefin.onefin_wallet.dto.request.UserCreateRequest;
import com.onefin.onefin_wallet.dto.response.UserCreateResponse;
import com.onefin.onefin_wallet.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);

    User toUser(UserCreateResponse userCreateResponse);

//    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    UserCreateResponse toUserCreateResponse(User user);
}
