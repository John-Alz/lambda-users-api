import { APIGatewayProxyHandler } from "aws-lambda";
import { UserUseCase } from "../../../domain/useCase/UserUseCase";
import { UserDynamoAdapter } from "../../out/adapter/UserDynamoAdapter";
import { UserHandler } from "../../../application/handler/impl/UserHandler";
import { UserSqsAdapter } from "../../out/adapter/UserSqsAdapter";
import { UserSnsAdapter } from "../../out/adapter/UserSnsAdapter";

const adapter = new UserDynamoAdapter();
const adapterSqs = new UserSqsAdapter()
const adapaterSns = new UserSnsAdapter();
const useCase = new UserUseCase(adapter, adapterSqs, adapaterSns);
const handler = new UserHandler(useCase);

export const getUsers: APIGatewayProxyHandler = async () => {
  const users = await handler.getUsers();
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
    const user = await handler.saveUser(body)
    return {
        statusCode: 201,
        body: JSON.stringify(user)
    }
}