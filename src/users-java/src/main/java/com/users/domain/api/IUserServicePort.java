package com.users.domain.api;

import com.users.domain.model.UserModel;

public interface IUserServicePort {

    void deleteUser(String userId);

    void updateUser(String id, UserModel user);


}
