package com.users.domain.spi;

import com.users.domain.model.UserModel;

public interface IUserPersistencePort {

    void deleteUser(String userId);

    void updateUser(String id, UserModel user);

}
