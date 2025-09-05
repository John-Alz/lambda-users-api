package com.users.infrastructure.in.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.users.application.dto.request.UserRequestDto;
import com.users.application.dto.response.SuccessMessageResponseDto;
import com.users.application.dto.response.UserResponseDto;
import com.users.application.handler.IUserHandler;
import com.users.application.handler.impl.UserHandler;
import com.users.application.mapper.UserMapper;
import com.users.domain.spi.IUserPersistencePort;
import com.users.domain.usecase.UserUseCase;
import com.users.infrastructure.out.dynamo.adapter.UserPersistenceAdapter;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class UserRest {

    // DELETE
    public static class DeleteUserHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

        private static final IUserHandler userHandler;
        static {
            DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                    .region(Region.US_EAST_1)
                    .build();
            IUserPersistencePort adapter = new UserPersistenceAdapter(dynamoDbClient);
            UserUseCase userUseCase = new UserUseCase(adapter);
            UserMapper userMapper = new UserMapper();
            userHandler = new UserHandler(userUseCase, userMapper);
        }
        private static final ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        @Override
        public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
            System.out.println(">>> Entrando al DeleteUserHandler <<<");

            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

            try{
                String id = event.getPathParameters().get("id");
                System.out.println("PathParameters: " + event.getPathParameters());
                System.out.println("ID recibido: '" + id + "'");
                SuccessMessageResponseDto result = userHandler.deleteUser(id);

                response.setStatusCode(200);
                response.setBody(objectMapper.writeValueAsString(result));

            } catch (Exception e) {
                response.withStatusCode(400);
                response.setBody("Invalid request: " + e.getMessage());
            }
            return response;
        }
    }

    // =========================
    // PUT
    // =========================
    public static class UpdateUserHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

        private static final IUserHandler userHandler;
        static {
            DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                    .region(Region.US_EAST_1)
                    .build();
            IUserPersistencePort adapter = new UserPersistenceAdapter(dynamoDbClient);
            UserUseCase userUseCase = new UserUseCase(adapter);
            UserMapper userMapper = new UserMapper();
            userHandler = new UserHandler(userUseCase, userMapper);
        }
        private static final ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        @Override
        public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            try {
                String id = event.getPathParameters().get("id");
                UserRequestDto updatedUser = objectMapper.readValue(event.getBody(), UserRequestDto.class);

                SuccessMessageResponseDto result = userHandler.updateUser(id, updatedUser);
                response.setStatusCode(200);
                response.setBody(objectMapper.writeValueAsString(result));
            } catch (Exception e) {
                response.setStatusCode(400);
                response.setBody("Invalid request: " + e.getMessage());
            }
            return response;
        }
    }

}
