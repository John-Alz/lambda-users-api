package com.users.domain.api;

import com.users.domain.model.UserModel;

public interface IUserServicePort {

    UserModel deleteUser(Long userId);

    UserModel updateUser(Long id, UserModel user);


}
