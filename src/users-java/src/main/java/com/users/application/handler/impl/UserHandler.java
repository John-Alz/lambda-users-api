package com.users.application.handler.impl;

import com.users.application.dto.request.UserRequestDto;
import com.users.application.dto.response.UserResponseDto;
import com.users.application.handler.IUserHandler;
import com.users.application.mapper.UserMapper;
import com.users.domain.api.IUserServicePort;
import com.users.domain.model.UserModel;

public class UserHandler implements IUserHandler {

    private IUserServicePort userServicePort;
    private UserMapper userMapper;

    public UserHandler(IUserServicePort userServicePort, UserMapper userMapper) {
        this.userServicePort = userServicePort;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto deleteUser(Long id) {
        return userMapper.toResponseDto(userServicePort.deleteUser(id));
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        UserModel userModel = userMapper.toModel(userRequestDto);
        return userMapper.toResponseDto(userServicePort.updateUser(id, userModel));
    }


}
