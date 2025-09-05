package com.users.application.handler;

import com.users.application.dto.request.UserRequestDto;
import com.users.application.dto.response.SuccessMessageResponseDto;
import com.users.application.dto.response.UserResponseDto;

public interface IUserHandler {

    SuccessMessageResponseDto deleteUser(String id);

    SuccessMessageResponseDto updateUser(String id, UserRequestDto userRequestDto);

}
