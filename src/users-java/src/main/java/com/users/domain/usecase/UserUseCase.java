package com.users.domain.usecase;

import com.users.domain.api.IUserServicePort;
import com.users.domain.model.UserModel;
import com.users.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserModel deleteUser(Long userId) {
        return userPersistencePort.deleteUser(userId);
    }

    @Override
    public UserModel updateUser(Long id, UserModel user) {
        return userPersistencePort.updateUser(id, user);
    }
}
