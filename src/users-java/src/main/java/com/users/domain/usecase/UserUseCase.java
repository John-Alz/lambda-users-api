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
    public void deleteUser(String userId) {
        userPersistencePort.deleteUser(userId);
    }

    @Override
    public void updateUser(String id, UserModel user) {
        userPersistencePort.updateUser(id, user);
    }
}
