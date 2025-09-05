package com.users.domain.spi;

import com.users.domain.model.UserModel;

public interface IUserPersistencePort {

    UserModel deleteUser(Long userId);

    UserModel updateUser(Long id, UserModel user);

}
