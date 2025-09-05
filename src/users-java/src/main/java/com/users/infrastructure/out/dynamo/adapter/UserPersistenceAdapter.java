package com.users.infrastructure.out.dynamo.adapter;

import com.users.domain.model.UserModel;
import com.users.domain.spi.IUserPersistencePort;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

public class UserPersistenceAdapter implements IUserPersistencePort {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "users";

    public UserPersistenceAdapter(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void deleteUser(String userId) {
        System.out.println("infrastructure.out.dynamo.adapter: ID a eliminar: " + userId);
        try {
            DeleteItemRequest request = DeleteItemRequest.builder()
                    .tableName(tableName)
                    .key(Map.of("id", AttributeValue.builder().s(userId).build()))
                    .build();
            dynamoDbClient.deleteItem(request);
            System.out.println("Usuario eliminado con éxito: " + userId);
        } catch (DynamoDbException e) {
            System.err.println("Error eliminando usuario: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateUser(String id, UserModel user) {
        try {
            UpdateItemRequest request = UpdateItemRequest.builder()
                    .tableName(tableName)
                    .key(Map.of("id", AttributeValue.builder().s(id).build()))
                    .updateExpression("SET #n = :name, #e = :email")
                    .expressionAttributeNames(Map.of("#n", "name", "#e", "email"))
                    .expressionAttributeValues(Map.of(
                            ":name", AttributeValue.builder().s(user.getName()).build(),
                            ":email", AttributeValue.builder().s(user.getEmail()).build()
                    ))
                    .build();
            dynamoDbClient.updateItem(request);
            System.out.println("Usuario actualizado: " + id);
        } catch (DynamoDbException e) {
            System.err.println("Error actualizando usuario: " + e.getMessage());
            throw e;
        }
    }

}
