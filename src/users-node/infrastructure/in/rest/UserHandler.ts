import { APIGatewayProxyHandler } from "aws-lambda";
import { UserUseCase } from "../../../domain/useCase/UserUseCase";
import { UserDynamoAdapter } from "../../out/adapter/UserDynamoAdapter";
import { UserHandler } from "../../../application/handler/impl/UserHandler";

const adapter = new UserDynamoAdapter();
const useCase = new UserUseCase(adapter);
const handler = new UserHandler(useCase);

export const getUsers: APIGatewayProxyHandler = async () => {
  const users = handler.getUsers();
  return {
    statusCode: 200,
    body: JSON.stringify(users),
  };
};

export const saveUser: APIGatewayProxyHandler = async (event) => {
    if (!event.body) {
    return { statusCode: 400, body: "Missing request body" };
  }
    const body = JSON.parse(event.body);
    const user = handler.saveUser(body)
    return {
        statusCode: 201,
        body: JSON.stringify(user)
    }
}