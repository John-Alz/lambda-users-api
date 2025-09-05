package com.users.application.handler.impl;

import com.users.application.dto.request.UserRequestDto;
import com.users.application.dto.response.SuccessMessageResponseDto;
import com.users.application.dto.response.UserResponseDto;
import com.users.application.handler.IUserHandler;
import com.users.application.mapper.UserMapper;
import com.users.domain.api.IUserServicePort;
import com.users.domain.model.UserModel;

import java.time.LocalDateTime;

public class UserHandler implements IUserHandler {

    private IUserServicePort userServicePort;
    private UserMapper userMapper;

    public UserHandler(IUserServicePort userServicePort, UserMapper userMapper) {
        this.userServicePort = userServicePort;
        this.userMapper = userMapper;
    }

    @Override
    public SuccessMessageResponseDto deleteUser(String id) {
        userServicePort.deleteUser(id);
        return new SuccessMessageResponseDto("Usuario con Id: " +  id + " Eliminado", LocalDateTime.now());
    }

    @Override
    public SuccessMessageResponseDto updateUser(String id, UserRequestDto userRequestDto) {
        UserModel userModel = userMapper.toModel(userRequestDto);
        userServicePort.updateUser(id, userModel);
        return new SuccessMessageResponseDto("Usuario con el Id: " + id + " actualizado", LocalDateTime.now());
    }


}
