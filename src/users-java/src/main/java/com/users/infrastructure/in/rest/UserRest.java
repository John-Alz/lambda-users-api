package com.users.infrastructure.in.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.users.application.dto.request.UserRequestDto;
import com.users.application.dto.response.UserResponseDto;
import com.users.application.handler.IUserHandler;
import com.users.application.handler.impl.UserHandler;
import com.users.application.mapper.UserMapper;
import com.users.domain.spi.IUserPersistencePort;
import com.users.domain.usecase.UserUseCase;
import com.users.infrastructure.out.dynamo.adapter.UserPersistenceAdapter;

public class UserRest {

    // DELETE
    public static class DeleteUserHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

        private static final IUserHandler userHandler;
        static {
            IUserPersistencePort adapter = new UserPersistenceAdapter();
            UserUseCase userUseCase = new UserUseCase(adapter);
            UserMapper userMapper = new UserMapper();
            userHandler = new UserHandler(userUseCase, userMapper);
        }
        private static final ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        @Override
        public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

            try{
                Long id = Long.parseLong(event.getPathParameters().get("id"));
                UserResponseDto result = userHandler.deleteUser(id);

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
            IUserPersistencePort adapter = new UserPersistenceAdapter();
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
                Long id = Long.parseLong(event.getPathParameters().get("id"));
                UserRequestDto updatedUser = objectMapper.readValue(event.getBody(), UserRequestDto.class);

                UserResponseDto result = userHandler.updateUser(id, updatedUser);
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
