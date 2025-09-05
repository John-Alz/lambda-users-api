package com.users.infrastructure.out.dynamo.adapter;

import com.users.domain.model.UserModel;
import com.users.domain.spi.IUserPersistencePort;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


import java.util.ArrayList;
import java.util.List;

public class UserPersistenceAdapter implements IUserPersistencePort {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "users";

    public UserPersistenceAdapter(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
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
