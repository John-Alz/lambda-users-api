package com.users.application.handler;

import com.users.application.dto.request.UserRequestDto;
import com.users.application.dto.response.UserResponseDto;

public interface IUserHandler {

    UserResponseDto deleteUser(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

}
