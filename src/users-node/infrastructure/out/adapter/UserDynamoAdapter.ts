import { IUserPersistencePort } from "../../../domain/spi/IUserPersistencePort";
import { DynamoDBClient } from "@aws-sdk/client-dynamodb";
import { DynamoDBDocumentClient, PutCommand, ScanCommand } from "@aws-sdk/lib-dynamodb";
import { v4 as uuidv4 } from "uuid";

export class UserDynamoAdapter implements IUserPersistencePort {

  private ddbDocClient: DynamoDBDocumentClient;
  private tableName = "users";

  constructor() {
    const client = new DynamoDBClient({ region: "us-east-1" });
    this.ddbDocClient = DynamoDBDocumentClient.from(client);
  }

  async getUsers(): Promise<User[]> {
    try {
      const command = new ScanCommand({ TableName: this.tableName });
      const result = await this.ddbDocClient.send(command);
      return (result.Items ?? []) as User[];
    } catch (error) {
      console.error("Error fetching users from DynamoDB:", error);
      throw error;
    }
  }

  async saveUser(user: User): Promise<User> {
    try {
      const newUser = { ...user, id: uuidv4() };
      console.log("Saving user:", newUser);

      const command = new PutCommand({
        TableName: this.tableName,
        Item: newUser,
      });
      await this.ddbDocClient.send(command);
      return newUser;
    } catch (error) {
      console.error("Error saving user to DynamoDB:", error);
      throw error;
    }
  }
}
