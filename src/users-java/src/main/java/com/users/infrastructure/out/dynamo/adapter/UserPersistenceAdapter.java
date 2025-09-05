package com.users.infrastructure.out.dynamo.adapter;

import com.users.domain.model.UserModel;
import com.users.domain.spi.IUserPersistencePort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserPersistenceAdapter implements IUserPersistencePort {

    List<UserModel> users = new ArrayList<>();
    {
        users.add(new UserModel(1L, "Juan Pérez", "juan@example.com"));
        users.add(new UserModel(2L, "María Gómez", "maria@example.com"));
    }

    @Override
    public UserModel deleteUser(Long userId) {
       UserModel userFound = users.stream()
               .filter(user -> user.getId().equals(userId))
               .findFirst()
               .orElse(null);
       if (userFound != null) {
           users.remove(userFound);
       }
       return userFound;
    }

    @Override
    public UserModel updateUser(Long id, UserModel user) {
        UserModel userFound = users.stream()
                .filter(us -> us.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (userFound != null) {
            userFound.setName(user.getName());
            userFound.setEmail(user.getEmail());
        }
        return userFound;
    }

}
