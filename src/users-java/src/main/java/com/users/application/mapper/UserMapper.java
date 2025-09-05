package com.users.application.mapper;

import com.users.application.dto.request.UserRequestDto;
import com.users.application.dto.response.UserResponseDto;
import com.users.domain.model.UserModel;

public class UserMapper {

    public UserResponseDto toResponseDto(UserModel userModel) {
        return new UserResponseDto(userModel.getId(), userModel.getName(),  userModel.getEmail());
    }

    public UserModel toModel(UserRequestDto userRequestDto) {
        return new UserModel(userRequestDto.name(), userRequestDto.email());
    }

}
